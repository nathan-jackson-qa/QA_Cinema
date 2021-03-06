package models

import slick.jdbc.MySQLProfile.api._

case class Movie(id: Int, name: String, desc: String, director: String, img_url: String, classification: String, isReleased: Boolean)

case class Movies(tag: Tag) extends Table[Movie](tag, "movies"){
  def id = column[Int]("MovieID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("Title")
  def desc = column[String]("Description")
  def director = column[String]("Director")
  def img_url = column[String]("Image_URL")
  def classification = column[String]("Classification")
  def isReleased = column[Boolean]("isReleased")
  def * = (id, name, desc, director, img_url, classification, isReleased) <> (Movie.tupled, Movie.unapply)
}
