package dao
import models.{Venue, Venues}
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object VenuesDAO {

  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val table: TableQuery[Venues] = TableQuery[Venues]

  def all: Future[Seq[Venue]] = {
    db.run(table.result)
  }

}
