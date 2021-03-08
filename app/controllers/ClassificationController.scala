package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class ClassificationController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def classification = Action {
    Ok(views.html.Classifications())
  }
}
