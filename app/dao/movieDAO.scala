package dao

import models.{Actor, Actors, Movie, Movie_Actors, Movies}
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object movieDAO {
  lazy val db: Database = Database.forConfig("mysqlDB")
  lazy val movies: TableQuery[Movies] = TableQuery[Movies]
  lazy val moviesActors: TableQuery[Movie_Actors] = TableQuery[Movie_Actors]
  lazy val actors: TableQuery[Actors] = TableQuery[Actors]

  def getMovieDetails(id: Int): Future[Seq[Movie]] = {
    db.run(movies.filter(t => t.id === id).result)
  }

  def getAllMovies: Future[Seq[Movie]] = {
    db.run(movies.result)
  }

  def getMovieActors: Future[Any] = {
    val actorsMovies = for {
      m  <- movies
      am <- moviesActors if m.id === am.movieID
      a <- actors if am.actorID === a.id
    } yield (m, a)
    db.run(actorsMovies.result)

  }
}