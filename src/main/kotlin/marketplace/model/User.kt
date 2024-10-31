package marketplace.model

import org.jetbrains.exposed.dao.id.UUIDTable

const val MAX_USER_NAME_LENGTH = 50

object User : UUIDTable("User") {
  val email = varchar("email", MAX_USER_NAME_LENGTH)
  val password = varchar("password", MAX_USER_NAME_LENGTH)
  val name = varchar("name", MAX_USER_NAME_LENGTH)
  val createdAt = text("created_at").default("CURRENT_TIMESTAMP")
  val updatedAt = text("updated_at").default("CURRENT_TIMESTAMP")
  val active = bool("active").default(false) // need to verify
}

