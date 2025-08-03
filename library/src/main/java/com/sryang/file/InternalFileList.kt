package com.sryang.file

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun InternalFileList() {
    var fileName: TextFieldValue by remember { mutableStateOf(TextFieldValue()) }
    var fileContents: TextFieldValue by remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current
    var fileList = context.fileList();
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column() {
            TextField(value = fileName, onValueChange = { fileName = it }, placeholder = {
                Text("fileName")
            })
            Spacer(Modifier.height(8.dp))
            TextField(value = fileContents, onValueChange = { fileContents = it }, placeholder = {
                Text("fileContents")
            })
            Spacer(Modifier.height(8.dp))
            Row {
                Button({
                    if (fileName.text.isNotEmpty() && fileContents.text.isNotEmpty()) {
                        saveFile(context, fileName.text, fileContents.text)
                        fileName = fileName.copy("")
                        fileContents = fileContents.copy("")
                    }
                }) { Text("SAVE") }
                Spacer(Modifier.width(8.dp))
                Button({}) { Text("DELETE") }
            }
        }
        Spacer(Modifier.height(8.dp))
        Text("FileList")
        LazyColumn {
            items(fileList.size) {
                TextButton({
                    fileName = fileName.copy(fileList[it])
                    fileContents = fileContents.copy(loadFile(context, fileList[it]))
                }) {
                    Text(fileList[it])
                }
            }
        }
    }

}

fun saveFile(context: Context, fileName: String, fileContents: String) {
    context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
        it.write(fileContents.toByteArray())
    }
}

fun loadFile(context: Context, fileName: String): String {
    return context.openFileInput(fileName).bufferedReader().useLines { lines ->
        lines.fold("") { some, text ->
            "$some\n$text"
        }
    }
}