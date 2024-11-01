package marketplace.core.database

import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database
import java.io.File
import java.io.FileWriter

class DriverFactory() {
  fun createDriver(): Database {
    val dbFilePath = getPath(isDebug = System.getenv("DEBUG") == "true")
    println("Filepath $dbFilePath")
    val driver = Database.connect("jdbc:sqlite:$dbFilePath")
    return driver
  }

  private fun getPath(isDebug: Boolean): String {
    val propertyKey = if (isDebug) "java.io.tmpdir" else "user.dir"
    val parentFolderPath = System.getProperty(propertyKey) + "/db"
    val parentFolder = File(parentFolderPath)

    if (!parentFolder.exists()) {
      parentFolder.mkdirs()
    }

    val dbName = dotenv()["DB_Name"] ?: "primary.db"
    val dbFile = File("$parentFolderPath/$dbName")

    if (!File(dbFile.absolutePath).exists()) {
      FileWriter(dbFile).use { it.write("") }
    }

    return dbFile.absolutePath
  }
}
