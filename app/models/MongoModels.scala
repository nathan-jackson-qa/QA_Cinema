package models

import play.api.libs.json.{Json, OFormat}
import play.api.libs.json.__
import reactivemongo.bson.BSONObjectID
import reactivemongo.play.json._

case class CinemaMongo(
                        _id: Option[BSONObjectID],
                        name: String,
                        openingTimes: String,
                        address: String,
                        g_location: String,
                        parking: String,
                        directions: String,
                        img: String
                       )

case class ActorMongo(
                      _id: Option[BSONObjectID],
                      firstName: String,
                      lastName: String
                )

case class MovieMongo(
                       _id: Option[BSONObjectID],
                       title: String,
                       desc: String,
                       director: String,
                       actors: Seq[ActorMongo],
                       img: String,
                       classification: String,
                       isReleased: Boolean,
                     )
object JsonFormats {
  // Generates Writes and Reads for Feed and User thanks to Json Macros
  implicit val cinemaFormat: OFormat[CinemaMongo] = Json.format[CinemaMongo]
  implicit val actorFormat: OFormat[ActorMongo] = Json.format[ActorMongo]
  implicit val movieFormat: OFormat[MovieMongo] = Json.format[MovieMongo]
}