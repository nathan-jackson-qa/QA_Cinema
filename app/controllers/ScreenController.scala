package controllers.mysql

import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject

class ScreenController  @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def screen = Action {
    Ok(views.html.mysql.screenPage())
  }
}
