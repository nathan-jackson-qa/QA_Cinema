package models

import play.api.data.Form
import play.api.data.Forms.{boolean, default, mapping, number, of, text}
import play.api.data.format.Formats.doubleFormat
import play.api.libs.json.{Json, OFormat}
import play.api.libs.json.__
import reactivemongo.bson.BSONObjectID
import reactivemongo.bson.BSONDocument
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
                       actors: Seq[BSONDocument],
                       img: String,
                       classification: String,
                       isReleased: Boolean
                     )

case class BookingMongo(
                  name: String,
                  date: String,
                  time: String,
                  numOfAdult: Int,
                  numOfChildren: Int,
                  deluxe: Boolean,
                  concessions: Double,
                  total: Double,
                  movieID: Int,
                  cinemaID: Int
                  )

case class BookingData(
                        _id: Option[BSONObjectID],
                         name: String,
                         date: String,
                         time: String,
                         numOfAdult: Int,
                         numOfChildren: Int,
                         deluxe: Boolean,
                         concessions: Double,
                         total: Double,
                         movieID: Int,
                         cinemaID: Int
                       )

object bookingMongoForm {
  val form: Form[BookingMongo] = Form(
    mapping(
      "name" -> text,
      "date" -> text,
      "time" -> text,
      "numOfAdult" -> number(min = 0, max = 10),
      "numOfChild" -> number(min = 0, max = 10),
      "deluxe" -> boolean,
      "concessions" -> of(doubleFormat),
      "total" ->  of(doubleFormat),
      "movie_id" -> number,
      "cinema_id" -> number
    )(BookingMongo.apply)(BookingMongo.unapply)
  )
}

object JsonFormats {
  // Generates Writes and Reads for Feed and User thanks to Json Macros
  implicit val cinemaFormat: OFormat[CinemaMongo] = Json.format[CinemaMongo]
  implicit val actorFormat: OFormat[ActorMongo] = Json.format[ActorMongo]
  implicit val movieFormat: OFormat[MovieMongo] = Json.format[MovieMongo]
  implicit val bookingFormat: OFormat[BookingMongo] = Json.format[BookingMongo]
  implicit val bookingDataFormat: OFormat[BookingData] = Json.format[BookingData]
}