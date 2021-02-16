package controllers

import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject

class DiscussionController @Inject()(cc: ControllerComponents) extends AbstractController(cc){

  def index = Action {
    Ok(views.html.discussionPage())
  }
}
