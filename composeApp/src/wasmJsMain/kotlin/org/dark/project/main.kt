package org.dark.project

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}

val client = HttpClient(Js)

@Composable
fun getData(): String {

    var data by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        data = client.get("http://localhost:8080/api/hello").bodyAsText()
    }
    return data
}
