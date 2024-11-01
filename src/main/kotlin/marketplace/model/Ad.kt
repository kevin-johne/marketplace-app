package marketplace.model

import org.jetbrains.exposed.dao.id.CompositeIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

// These ads are created internally by the application
// there will be another table for ads ingested from the external sources
object Ad : UUIDTable("Ad") {
  val title = text("title")
  val description = text("description")
  val price = integer("price")
  val strokePrice = integer("stroke_price").nullable()
  val userId =
    reference("user_id", UserTable.id, onDelete = ReferenceOption.CASCADE)
  val createdAt = text("created_at").default("CURRENT_TIMESTAMP")
  val updatedAt = datetime("updated_at").nullable()
}

//object AdHistory : UUIDTable("Ad_history") {
//  val adId = reference("ad_id", Ad, onDelete = ReferenceOption.CASCADE)
//}

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

