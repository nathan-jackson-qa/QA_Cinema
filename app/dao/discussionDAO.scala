package dao

import models.{Discussion, Discussions}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object discussionDAO {
  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val table: TableQuery[Discussions] = TableQuery[Discussions]

  def create(discussion: Discussion): Future[String] = {
    db.run(table += discussion).map(res => "Discussion successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }
}
