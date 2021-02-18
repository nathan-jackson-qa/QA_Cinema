package models

import play.api.data.Form
import play.api.data.Forms._
import slick.jdbc.MySQLProfile.api._
import play.api.data.format.Formats._
import slick.sql.SqlProfile.ColumnOption.SqlType
import java.sql._

case class Booking(id: Int = 0, name_of_person: String, date: Date, time: Int,numOfAdult: Int, numOfChild: Int, deluxe: Boolean, concessions: Double, total: Double, movie_id: Int, cinema_id: Int)

case class Bookings(tag: Tag) extends Table[Booking](tag, "bookings") {
  def id = column[Int]("booking_id", O.PrimaryKey, O.AutoInc)

  def name_of_person = column[String]("name_of_person")

  def date = column[Date]("date")

  def time = column[Int]("time")

  def numOfAdult = column[Int]("numOfAdult")

  def numOfChild = column[Int]("numOfChild")

  def deluxe = column[Boolean]("deluxe")

  def concessions = column[Double]("concessions")

  def total = column[Double]("total")

  def movie_id = column[Int]("movie_id")

  def cinema_id = column[Int]("cinema_id")

  override def * = (id, name_of_person, date, time, numOfAdult, numOfChild, deluxe, concessions, total, movie_id, cinema_id) <> (Booking.tupled, Booking.unapply)

  def cinema = foreignKey("cinema_id", cinema_id, TableQuery[Cinemas])(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

  def movie = foreignKey("MovieID", movie_id, TableQuery[Movies])(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
}

object bookingForm {
  val form: Form[Booking] = Form(
    mapping(
      "id" -> number,
      "name_of_person" -> text,
      "date" -> sqlDate,
      "time" -> number,
      "numOfAdult" -> number(min = 0, max = 10),
      "numOfChild" -> number(min = 0, max = 10),
      "deluxe" -> boolean,
      "concessions" -> of(doubleFormat),
      "total" ->  of(doubleFormat),
      "movie_id" -> number,
      "cinema_id" -> number
    )(Booking.apply)(Booking.unapply)
  )
}