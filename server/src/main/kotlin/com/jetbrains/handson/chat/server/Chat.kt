package com.jetbrains.handson.chat.server

import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import java.util.*


fun Route.chatRoute() {
    val connections = Collections.synchronizedSet(LinkedHashSet<Connection>())

    webSocket("/chat") {
        connections += Connection(this)
        try {
            while (true) {
                when (val frame = incoming.receive()) {
                    is Frame.Text -> {
                        val receivedText = frame.readText()
                        connections.forEach {
                            val textToSend = "[${it.name}] $receivedText"
                            it.session.outgoing.send(Frame.Text(textToSend))
                        }
                    }
                }
            }
        } finally {
            connections -= Connection(this)
        }
    }
}

fun Application.registerChatRoutes() {
    routing {
        chatRoute()
    }
}