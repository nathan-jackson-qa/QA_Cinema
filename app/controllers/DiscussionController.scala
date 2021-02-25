package controllers

import dao.discussionDAO
import models.{Discussion, discussionForm}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class DiscussionController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def index() = Action async {
    discussionDAO.getReviews map {
      results => Ok(views.html.discussionPage(results))
    }
  }

  def discForm() = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.discussionPage2(discussionForm.form))
  }

  def discFormPost() = Action { implicit request: Request[AnyContent] =>
    discussionForm.form.bindFromRequest().fold({ formWithErrors => BadRequest(views.html.discussionPage2(formWithErrors)) },
      { widget => {
        if (split(widget.description) == true || split(widget.title) == true) {
          discussionDAO.create(Discussion(widget.id, widget.title, widget.description, widget.rating, false))
          Ok(views.html.moderation(widget, "UNSUCCESSFUL"))
        }
        else {
          discussionDAO.create(Discussion(widget.id, widget.title, widget.description, widget.rating, true))
          Ok(views.html.moderation(widget, "SUCCESSFUL"))
        }
      }
      }
    )
  }

  def split(text: String): Boolean = {
    val words = text.split(" ")
    var overall = false
    for (word <- words) {
      if (check(word) == true) {
        overall = true;
      }
    }
    overall
  }

  def check(word: String): Boolean = {
    val badWords = scala.io.Source.fromFile("profanityFilter.txt").getLines.flatMap(_.split("\\W+")).toList
    badWords.contains(word)
  }


}

