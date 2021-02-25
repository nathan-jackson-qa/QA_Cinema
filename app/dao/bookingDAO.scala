package dao

import dao.cinemaDAO.{db, table}
import models.{Booking, Bookings, Cinema}
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object bookingDAO {
  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val table: TableQuery[Bookings] = TableQuery[Bookings]

  def add(booking: Booking): Future[String] = {
    db.run(table += booking).map(res => "Booking successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

}

