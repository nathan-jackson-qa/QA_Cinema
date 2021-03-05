package models

import play.api.libs.json.{Json, OFormat}


case class CinemaMongo(
                        name: String,
                        openingTimes: String,
                        address: String,
                        g_location: String,
                        parking: String,
                        directions: String,
                        img: String
                       )

object JsonFormats {
  // Generates Writes and Reads for Feed and User thanks to Json Macros
  implicit val cinemaFormat: OFormat[CinemaMongo] = Json.format[CinemaMongo]
}