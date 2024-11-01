package marketplace.model

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.util.UUID

object UserTable : IdTable<UUID>("User") {
  override val id = uuid("id").autoGenerate().entityId()
  val email = text("email").entityId()
  val password = text("password")
  val userName = text("user_name").entityId()
  val firstName = text("first_name")
  val lastName = text("last_name")
  val createdAt = text("created_at").default("CURRENT_TIMESTAMP")
  val updatedAt = datetime("updated_at").nullable()
  val isActive = bool("is_active").default(false) // need to verify
  val deactivatedAt = text("deactivated_at").nullable()

  // when deleted is triggered we should deduct all the sensitive data of the user
  val deletedAt = text("deleted_at").nullable()

  override val primaryKey = PrimaryKey(id, email)
}

// invalidate token when expired or a newer token is generated
object PasswordResetRequest : UUIDTable("Password_Reset_Request") {
  val email = text("email")
  val createdAt = text("created_at").default("CURRENT_TIMESTAMP")
  val expiresAt = text("expires_at")
  val token = uuid("token")
  val userId =
    reference("user_id", UserTable.id, onDelete = ReferenceOption.NO_ACTION)
}
