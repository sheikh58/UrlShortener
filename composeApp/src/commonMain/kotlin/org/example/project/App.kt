package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.project.network.ApiService
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(appContext: Any? = null) {
    MaterialTheme {

        var urlValue by remember { mutableStateOf("") }
        var urlResult by remember { mutableStateOf("") }
        val coroutineScope = rememberCoroutineScope()
        var loadingState by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Url Shortener")
            TextField(
                value = urlValue,
                onValueChange = { urlValue = it },
                label = { Text("Enter the url") },
            )

            when (loadingState) {
                true -> {
                    Spacer(modifier = Modifier.height(10.dp))
                    CircularProgressIndicator()
                }

                false -> {
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = {
                        loadingState = true
                        coroutineScope.launch {
                            urlResult = ApiService().makeShortUrl(urlValue)
                            loadingState = false
                        }
                    }) {
                        Text("Shorten")
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row {
                Text("Short url : ")
                Text(urlResult)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                openUrl(urlResult, context = appContext)
            }) {
                Text("Open the url")
            }
        }
    }
}