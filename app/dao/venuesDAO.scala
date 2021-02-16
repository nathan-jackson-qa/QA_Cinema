package dao
import models.{Venue, Venues}
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object venuesDAO {

  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val table: TableQuery[Venues] = TableQuery[Venues]

  def getAllVenues: Future[Seq[Venue]] = {
    db.run(table.result)
  }

  def getAllVenuesById(id: Int): Future[Seq[Venue]] = {
    db.run(table.filter(t => t.cinema_id === id).result)
  }

}
