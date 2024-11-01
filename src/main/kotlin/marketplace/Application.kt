package marketplace

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import marketplace.plugins.configureDatabases
import marketplace.plugins.configureRouting
import marketplace.plugins.configureSecurity
import marketplace.plugins.configureSerialization

fun main(args: Array<String>) {
  embeddedServer(Netty, port = 8080) {
    println("Server started")
    module()
  }.start(wait = true)
}


fun Application.module() {
  configureSecurity()
  configureDatabases()
  configureSerialization()
  configureRouting()
}
