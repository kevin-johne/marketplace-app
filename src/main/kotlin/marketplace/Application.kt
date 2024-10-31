package marketplace

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import isDevelopment
import marketplace.core.database.DriverFactory
import marketplace.core.database.createDatabase
import marketplace.model.*
import marketplace.plugins.configureRouting
import marketplace.plugins.configureSecurity
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

//import io.ktor.server.plugins.cont.*

fun main(args: Array<String>) {


  embeddedServer(Netty, port = 8080) {
    println("Server started")
    install(ContentNegotiation)
    module()
    // ...
  }.start(wait = true)
}


fun Application.module() {
  configureSecurity()
  configureRouting()
  createDatabase(DriverFactory())
  TransactionManager.manager.defaultIsolationLevel =
    Connection.TRANSACTION_SERIALIZABLE
  transaction {
    if (isDevelopment()) {
      addLogger(StdOutSqlLogger)
    }
    SchemaUtils.create(
      User,
      Ad,
      AdHistory,
      File,
      AdImages,
      SearchHistory,
      Location
    )

    User.selectAll().forEach {
      println(it)
    }
  }
}
