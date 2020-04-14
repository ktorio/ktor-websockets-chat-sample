package com.jetbrains.handson.chat.client

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import javafx.scene.control.Tooltip.*
import kotlinx.coroutines.*
import kotlin.system.*

fun main() = runBlocking {
    val client = HttpClient {
        install(WebSockets)
    }
        client.webSocket(
                method = HttpMethod.Get,
                host = "0.0.0.0",
                port = 8080, path = "/chat"
        ) {
            var message = readLine() ?: ""
            while (message.compareTo("exit", true) != 0) {
                send(message)
                when (val frame = incoming.receive()) {
                    is Frame.Text -> println(frame.readText())
                    is Frame.Binary -> println(frame.readBytes())
                }
                message = readLine() ?: ""
            }
        }
        client.close()
        println("Connection closed")
}