package marketplace.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import marketplace.core.authorization.loginRoute

fun Application.configureRouting() {
  routing {
    loginRoute()
    get("/") {
      call.respondText(
        "Hello World!"
      )
    }
  }
}
