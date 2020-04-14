package com.jetbrains.handson.chat.server

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.websocket.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
   install(WebSockets)
   registerChatRoutes()
}
