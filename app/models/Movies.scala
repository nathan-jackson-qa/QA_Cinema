package models
import slick.jdbc.MySQLProfile.api._

case class Movie(id: Int, name: String, desc: String, actors: String, director: String, img_url: String, classification: String)

case class Movies(tag: Tag) extends Table[Movie](tag, "movies"){
  def id = column[Int]("MovieID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("Title")
  def desc = column[String]("Description")
  def actors = column[String]("Actors")
  def director = column[String]("Director")
  def img_url = column[String]("Image_URL")
  def classification = column[String]("Classification")
  def * = (id, name, desc, actors, director, img_url, classification) <> (Movie.tupled, Movie.unapply)
}
