package marketplace.core.authorization

import io.ktor.server.request.*
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import marketplace.model.UserTable
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.loginRoute() {
  post("api/login") {
    val uri = call.request.uri
    call.receive<LoginRequest>()
    call.respondText("Hello World!")
    // authenticate against the database
    // store the current token in the db
    // redirect to the home page
    // add the token to the cookie
  }
}

class LoginRequest(val username: String, val password: String) {
  suspend fun validate() {
    if (username.isBlank() || password.isBlank()) {
      throw IllegalArgumentException("username and password are required")
    }

    transaction {
      UserTable.select(column = UserTable.password).where {
        UserTable.userName eq username
        UserTable.isActive eq true
        UserTable.deactivatedAt eq null
        UserTable.deletedAt eq null
      }
    }

  }
}

