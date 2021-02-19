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

  def discFormPost() = Action { implicit request : Request[AnyContent] =>
      discussionForm.form.bindFromRequest().fold({ formWithErrors => BadRequest(views.html.discussionPage2(formWithErrors))},
        { widget => discussionDAO.create(widget)
        Redirect(routes.DiscussionController.index())}
    )
  }
  def index2() = Action async {
    discussionDAO.getReviews map {
      results => Ok(views.html.admin(results))
    }
  }

}

