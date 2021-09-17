package com.jetbrains.handson.chat.server

import io.ktor.server.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import java.util.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {

}
