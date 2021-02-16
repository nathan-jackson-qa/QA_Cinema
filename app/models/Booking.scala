package models

import play.api.data.Form
import play.api.data.Forms._
import slick.jdbc.MySQLProfile.api._
import slick.sql.SqlProfile.ColumnOption.SqlType

import java.sql._

case class Booking(id: Int = 0, name_of_person: String, dateTime: Date, numOfAdult: Int, numOfChild: Int, deluxe: Boolean, concessions: Boolean, total: Int, movie_id: Int, cinema_id: Int)

case class Bookings(tag: Tag) extends Table[Booking](tag, "bookings") {
  def id = column[Int]("booking_id", O.PrimaryKey, O.AutoInc)

  def name_of_person = column[String]("name_of_person")

  def date = column[Date]("date", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def numOfAdult = column[Int]("numOfAdult")

  def numOfChild = column[Int]("numOfChild")

  def deluxe = column[Boolean]("deluxe")

  def concessions = column[Boolean]("concessions")

  def total = column[Int]("concessions")

  def movie_id = column[Int]("movie_id")

  def cinema_id = column[Int]("cinema_id")

  override def * = (id, name_of_person, date, numOfAdult, numOfChild, deluxe, concessions, total, movie_id, cinema_id) <> (Booking.tupled, Booking.unapply)

  def cinema = foreignKey("cinema_id", cinema_id, TableQuery[Cinemas])(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

  def movie = foreignKey("MovieID", movie_id, TableQuery[Movies])(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
}

object bookingForm {
  val form: Form[Booking] = Form(
    mapping(
      "id" -> number,
      "name_of_person" -> text,
      "dateTime" -> sqlDate,
      "numOfAdult" -> number,
      "numOfChild" -> number,
      "deluxe" -> boolean,
      "concessions" -> boolean,
      "total" -> number,
      "movie_id" -> number,
      "cinema_id" -> number
    )(Booking.apply)(Booking.unapply)
  )
}