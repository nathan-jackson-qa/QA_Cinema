package dao
import models.{Venue, Venues}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.Future

object venuesDAO {

  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val table: TableQuery[Venues] = TableQuery[Venues]

//  def getAllVenues: Future[Seq[Venue]] = {
//    db.run(table.result)
//  }

  def getAllVenuesById(id: Int): Future[Seq[Venue]] = {
    db.run(table.filter(t => t.cinema_id === id).result)
  }

}
