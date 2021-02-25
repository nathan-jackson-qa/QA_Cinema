package models

import play.api.data.Forms._
import play.api.data._
import play.api.data.validation.Constraints._
import slick.jdbc.MySQLProfile.api._

case class Discussion(id: Int, title: String, description: String, rating: Int, onApproved: Boolean)
//{
//  def getId() = id
//  def getTitle() = title
//  def getDescription() = description
//  def getRating() = rating
//  def getApproved() = onApproved
//}

case class Discussions(tag: Tag) extends Table[Discussion](tag, "discussion"){
  def id = column[Int]("Discussion_ID", O.PrimaryKey, O.AutoInc)

  def title = column[String]("Title")

  def description = column[String]("Description")

  def rating = column[Int]("Rating")

  def onApproved = column[Boolean]("onApproved")

  def * = (id, title, description, rating, onApproved) <> (Discussion.tupled, Discussion.unapply)
}

object discussionForm {
  val form: Form[Discussion] = Form(
    mapping(
      "id" -> number,
      "title" -> text,
      "description" -> text,
      "rating" -> number.verifying(min(0), max(10)),
      "onApproved" -> boolean
    )(Discussion.apply)(Discussion.unapply)
  )
}
