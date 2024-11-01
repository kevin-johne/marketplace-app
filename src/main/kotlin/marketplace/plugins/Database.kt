package marketplace.plugins

import io.ktor.server.application.*
import marketplace.core.database.DriverFactory
import marketplace.model.*
import marketplace.utils.isDevelopment
import org.jetbrains.exposed.sql.Schema
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

fun Application.configureDatabases() {
  DriverFactory().createDriver()
  TransactionManager.manager.defaultIsolationLevel =
    Connection.TRANSACTION_SERIALIZABLE
  transaction {

    if (isDevelopment()) {
      addLogger(StdOutSqlLogger)
    }

    // create schema
    val schema = Schema("application")
    SchemaUtils.createSchema(schema)
    SchemaUtils.setSchema(schema)

    // create tables
    SchemaUtils.create(
      UserTable,
      Ad,
//      AdHistory,
      File,
      AdImages,
      SearchHistory,
      Location
    )
  }
}