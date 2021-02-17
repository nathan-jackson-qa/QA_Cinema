package models

import slick.jdbc.MySQLProfile.api._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import views.html.helper.form

case class Discussion(id: Int, title: String, description: String, rating: Int) {
  def getId() = id
  def getTitle() = title
  def getDescription() = description
  def getRating() = rating
}

case class Discussions(tag: Tag) extends Table[Discussion](tag, "discussion"){
  def id = column[Int]("Discussion_ID", O.PrimaryKey, O.AutoInc)

  def title = column[String]("Title")

  def description = column[String]("Description")

  def rating = column[Int]("Rating")

  def * = (id, title, description, rating) <> (Discussion.tupled, Discussion.unapply)
}

object discussionForm {
  val form: Form[Discussion] = Form(
    mapping(
      "id" -> number,
      "title" -> text,
      "description" -> text,
      "rating" -> number.verifying(min(0), max(10))
    )(Discussion.apply)(Discussion.unapply)
  )
}
