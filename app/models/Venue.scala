package models
import slick.jdbc.MySQLProfile.api._
case class Venue(id: Int = 0, name: String, address: String, image: String, offers: String, web_address: String, cinema_id: Int, description: String)

case class Venues(tag: Tag) extends Table[Venue](tag, "venues"){
  def id = column[Int]("venue_id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def address = column[String]("address")
  def image = column[String]("image")
  def offers = column[String]("offers")
  def web_address = column[String]("web_address")
  def cinema_id = column[Int]("cinema_id")
  def description = column[String]("description")

  override def * = (id, name, address, image, offers, web_address, cinema_id, description) <> (Venue.tupled, Venue.unapply)

  def cinema = foreignKey("cinema_id", cinema_id, TableQuery[Cinemas])(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)

}
