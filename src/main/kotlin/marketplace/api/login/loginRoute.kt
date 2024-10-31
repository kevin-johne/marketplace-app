package marketplace.api.login

import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.loginRoute() {
  post("api/login") {
    val uri = call.request.uri
    call.receive()
    // authenticate against the database
    // store the current token in the db
    // redirect to the home page
    // add the token to the cookie
  }
}