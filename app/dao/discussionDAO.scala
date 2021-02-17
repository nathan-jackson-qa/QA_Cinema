package dao

import models.{Discussion, Discussions}
import slick.lifted.TableQuery
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object discussionDAO {
  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val table: TableQuery[Discussions] = TableQuery[Discussions]

  def create(discussion: Discussion): Future[String] = {
    db.run(table += discussion).map(res => "Discussion successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }
}
