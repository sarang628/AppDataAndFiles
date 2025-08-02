https://developer.android.com/training/data-storage

안드로이드 파일 시스템은 다른 플랫폼들과 비슷한 disk-based 방식 사용.

데이터 저장을 위한 몇 가지 옵션 제공

App-specific storage - 앱 내부에서만 사용하는 용도. 민감 데이터 저장하는데 적합<br> 
Shared storage - 공유용 파일을 저장하는 용도. 미디어, 이미지, 문서 등 다양한 파일이 해당.<br>
Preference - 앱 에서만 사용. 키 - 벨류 방식.<br>
Databases - 앱 에서만 사용. 구조 데이터 저장 방식. room과 같은 라이브러리에서 사용되기도 함.

요구에 따라 옵션 고르기

많은 양의 데이터를 저장?
App-specific storage 한정된 공간을 제공함으로, 다른 옵션 사용하기. 

높은 신뢰성 필요?
외부 저장소는 물리적으로 제거 될 수 있으므로, App-specific storage 사용하기

어떤 종류의 데이터?
앱에서만 사용 App-specific storage, 공유용 데이터 Shared storage, 구조적 데이터 Databases or Preference

private data는 어디 저장?
internal storage or preference or databases. 이 옵션 들은 외부에서 접근 불가.

[Categories of storage locations](https://developer.android.com/training/data-storage#categories-locations)

내부, 외부 2가지 저장소 타입 제공.
내부 저장소는 기기에서 항상 접근 할 수 있어 신뢰성이 높음. 하지만 용량 제한이 있음.
외부 저장소는 /sdcard와 같은 경로로 제공됨.

주의 : 
1. 기기마다 저장소를 제공하는 경로가 다르므로 경로 하드코딩 하지 말 것.
2. 파일 저장 시 정보가 노출될 만한 패턴으로 저장하지 않기.

앱 용량이 클 경우 외부 저장소에 저장을 유도하는 방법

```
<manifest ...
  android:installLocation="preferExternal">
  ...
</manifest>
```

[Permissions and access to external storage](https://developer.android.com/training/data-storage#permissions)

이전 권한인 READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE 이 권한은 앱 내부 저장소 외에 어디서나 읽고 쓰기가 가능하였다.

최신 버전에서 데이터는 목적 기반 저장소(purpose-base storage)로 파일의 위치보단 목적에 초점을 맞춰 파일 접근 방식이 달라졌다.

WRITE_EXTERNAL_STORAGE 는 android 11 (api 30) 