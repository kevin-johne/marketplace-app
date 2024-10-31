package marketplace.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import marketplace.api.login.loginRoute
import marketplace.core.database.DriverFactory
import marketplace.core.database.createDatabase
import marketplace.model.User
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {
  routing {
    loginRoute()
    get("/") {
//      val driver = createDatabase(DriverFactory())
//      val results = driver.databaseQueries.GetAllAds().executeAsList()
      createDatabase(DriverFactory())
      transaction {
        println("get users")
        User.selectAll().forEach {
          println(it)
        }
      }
      call.respondText(
        "Hello World!"
      )
    }
  }
}
