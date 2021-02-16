package dao

import dao.cinemaDAO.{db, table}
import models.{Booking, Bookings, Cinema}
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

object bookingDAO {
  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val table: TableQuery[Bookings] = TableQuery[Bookings]

  def add(booking: Booking): Future[Int] = {
    db.run(table += booking)
  }

}
