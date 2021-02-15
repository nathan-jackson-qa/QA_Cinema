package models

import slick.jdbc.MySQLProfile.api._


case class Cinema(id: Int = 0, name: String, opening_times: String, address: String, g_location: String, parking: String, directions: String, img_src: String){
def getId() = id
def getName() = name
def getOpening_times() = opening_times
def getAddress() = address
def getG_location() = g_location
def getParking() = parking
def getDirection() = directions
def getImg_src() = img_src
}

case class Cinemas(tag: Tag) extends Table[Cinema](tag, "cinemas") {
  def id = column[Int]("Cinema_ID", O.PrimaryKey, O.AutoInc)

  def name = column[String]("Cinema_Name")

  def opening_times = column[String]("Opening_Times")

  def address = column[String]("Address")

  def g_location = column[String]("G_Location")

  def parking = column[String]("Parking")

  def directions = column[String]("Direction_Desc")

  def img_src = column[String]("Img_Src")

  def * = (id, name, opening_times, address, g_location, parking, directions, img_src) <> (Cinema.tupled, Cinema.unapply)

}

