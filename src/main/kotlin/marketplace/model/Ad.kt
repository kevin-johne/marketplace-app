package marketplace.model

import org.jetbrains.exposed.dao.id.CompositeIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption

object Ad : UUIDTable("Ad") {
  val title = text("title")
  val description = text("description")
  val price = integer("price")
  val strokePrice = integer("stroke_price")
  val userId = reference("user_id", User, onDelete = ReferenceOption.CASCADE)
}

object AdHistory : UUIDTable("Ad_history") {
  val createdAt = text("created_at").default("CURRENT_TIMESTAMP")
  val adId = reference("ad_id", Ad, onDelete = ReferenceOption.CASCADE)
}

object File : UUIDTable("File") {
  val bucket = text("bucket")
  val s3Url = text("s3")
  val uri = text("uri")
  val type = text("type")
  val extension = text("extension")
  val description = text("description")
}

object AdImages : CompositeIdTable("Ad_images") {
  val adId = reference("ad_id", Ad, onDelete = ReferenceOption.CASCADE)
  val fileId =
    reference("file_id", File, onDelete = ReferenceOption.CASCADE)
}

