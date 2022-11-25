// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import parse.pdf.parseToStyledWord
import parse.styledWordFromPdf.parseToWordWithCategory
import parse.wordWithCategory.parseToCommand
import java.io.File

private const val PDF_DIR = "src/jvmMain/resources/manual_trim.pdf"

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
            val styledWords = File(PDF_DIR).parseToStyledWord()
            val wordsWithCategory = styledWords.parseToWordWithCategory()
            val commands = wordsWithCategory.parseToCommand()
            println(commands.joinToString("\n"))
        }) {
            Text(text)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
