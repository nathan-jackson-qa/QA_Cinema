package dao

import models.{Cinema, Cinemas}
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object cinemaDAO {
  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val table: TableQuery[Cinemas] = TableQuery[Cinemas]

  def getCinemaDetails(id: Int): Future[Option[Cinema]] = {
    db.run(table.filter(_.id === id).result.headOption)
  }

  def getAllCinemas: Future[Seq[Cinema]] = {
    db.run(table.result)
  }
}
