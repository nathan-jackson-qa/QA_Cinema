package controllers

import dao.discussionDAO
import models.{Discussion, discussionForm}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import javax.inject.{Inject, Singleton}

@Singleton
class DiscussionController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def index = Action {
    Ok(views.html.discussionPage())
  }
  def discForm() = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.discussionPage2(discussionForm.form))
  }

  def discFormPost() = Action { implicit request : Request[AnyContent] =>
    println(discussionForm.form.bindFromRequest().get)
      discussionForm.form.bindFromRequest().fold({ formWithErrors => BadRequest(views.html.discussionPage2(formWithErrors))},
        { widget => discussionDAO.create(widget)
        Redirect(routes.DiscussionController.index())}
    )
  }
}

