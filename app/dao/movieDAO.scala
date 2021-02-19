package dao

import dao.venuesDAO.{db, table}
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

  def getMovieDetails(id: Int): Future[Option[Movie]] = {
    db.run(movies.filter(t => t.id === id).result.headOption)
  }

  def getAllMovies: Future[Seq[Movie]] = {
    db.run(movies.result)
  }

  def getMovieActors(id: Int): Future[Seq[Actor]] = {
    val actorsMovies = for {
      m  <- movies if m.id === id
      am <- moviesActors if m.id === am.movieID
      a <- actors if am.actorID === a.id
    } yield (a)
    db.run(actorsMovies.result)
  }

  def getReleasedMovies: Future[Seq[Movie]] = {
    db.run(movies.filter(t => t.isReleased === true).take(10).result)
  }

  def getUpcomingMovies: Future[Seq[Movie]] = {
    db.run(movies.filter(t => t.isReleased === false).result)
  }

  def searchBykeyword(keyword: String): Future[Seq[Movie]] = {
    db.run(movies.filter(t =>(t.name like s"%$keyword%") || (t.director like s"%$keyword%") || (t.desc like s"%$keyword%") || (t.classification like s"%$keyword%")).result)
  }
}