package dao

import models.{Movie, Movies}
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object movieDAO {
  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val table: TableQuery[Movies] = TableQuery[Movies]

  def getMovieDetails(id: Int): Future[Seq[Movie]] = {
    db.run(table.filter(t => t.id === id).result)
  }

  def getAllMovies: Future[Seq[Movie]] = {
    db.run(table.result)
  }

}