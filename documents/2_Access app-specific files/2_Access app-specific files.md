https://developer.android.com/training/data-storage/app-specific

앱에서만 접근 가능한 파일을 저장하려면 app-specific 사용

Internal storage directories
- persistent files 공간과 cache data 공간이 있음.
- Android 10 (API level 29) 이상에서 시스템은 이 공간의 접근을 막음
- 위치는 암호화됨.
- 민감 정보를 저장하는데 사용

External storage directories
- persistent files 공간과 cache data 공간이 있음.
- 권한이 있으면 다른앱에서 접근이 가능
- 공유할 목적으로 파일을 저장한다면 shared storage 사용하기

앱을 지우면 app-specific 파일들도 함께 삭제됨. 파일 용도를 잘 구분지어 저장해야 함.
예를들어 캡처 파일을 app-specific 에 저장하면 삭제할 때 지워지므로 shared storage에 저장 할 것.

[Access from internal storage](https://developer.android.com/training/data-storage/app-specific#internal)
모든 앱은 저장소가 제공됨. 

persistent files 공간과 cache data 공간이 있음. 

권한 없이 접근 가능.

다른 앱은 접근이 불가. 

공간이 적음.


[Access persistent files](https://developer.android.com/training/data-storage/app-specific#internal-access-files)
앱의 디렉토리는 context의 filesDir 속성으로 접근 할 수 있음. 프레임워크에서 여러 함수들을 제공

[Access and store files](https://developer.android.com/training/data-storage/app-specific#internal-access-store-files)

File API를 사용해 파일에 접근 및 저장.

앱의 퍼포먼스를 위해 동일한 파일을 여러번 열지 말 것.

사용법:

```
val file = File(context.filesDir, filename)
```

[Store a file using a stream](https://developer.android.com/training/data-storage/app-specific#internal-store-stream)

File API 대신 openFileOutput() 사용해 스트림 기능 사용 가능.

```
val filename = "myfile"
val fileContents = "Hello world!"
context.openFileOutput(filename, Context.MODE_PRIVATE).use {
        it.write(fileContents.toByteArray())
}
```

* Android 7.0 (API level 24)에서 Context.MODE_PRIVATE 사용안하면 SecurityException 발생

internal storage 라도 다른 앱에서 접근하게 하는 방법이 있음.
FileProvider 의 FLAG_GRANT_READ_URI_PERMISSION attribute를 사용하면 접근 가능.

[Access a file using a stream](https://developer.android.com/training/data-storage/app-specific#internal-access-stream)
파일 접근 하기

```
context.openFileInput(filename).bufferedReader().useLines { lines ->
    lines.fold("") { some, text ->
        "$some\n$text"
    }
}
```