package models

import slick.jdbc.MySQLProfile.api._
import slick.model.ForeignKeyAction

case class Actor(id: Int, firstName: String, lastName: String)
case class Movie_Actor(movieID: Int, ActorID: Int)

case class Actors(tag: Tag) extends Table[Actor](tag, "actors") {
  def id = column[Int]("ActorID", O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("FirstName")
  def lastName = column[String]("LastName")
  def * = (id, firstName, lastName) <> (Actor.tupled, Actor.unapply)
}

case class Movie_Actors(tag: Tag) extends Table[Movie_Actor](tag, "movie_actors") {
  def movieID = column[Int]("MovieID")
  def actorID = column[Int]("ActorID")
  def * = (movieID, actorID) <> (Movie_Actor.tupled, Movie_Actor.unapply)

  def movie = foreignKey("MovieFK", movieID, TableQuery[Movies])(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
  def actor = foreignKey("ItemFK", actorID, TableQuery[Actors])(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
}

// def item = foreignKey("ItemFK", itemID, TableQuery[Items])(_.itemId, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)