package marketplace.model

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption

object SearchHistory : UUIDTable("Search_history") {
  val createdAt = text("created_at").default("CURRENT_TIMESTAMP")
  val locationId = reference("location_id", Location)
  val distance = integer("distance")
  val term = text("term")
  val userId =
    optReference("user_id", UserTable.id, onDelete = ReferenceOption.CASCADE)
}

object Location : UUIDTable("Location") {
  val lat = double("lat")
  val lang = double("lang")
  val country = text("country")
  val city = text("city")
}