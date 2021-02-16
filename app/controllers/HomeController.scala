package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.homePage())
  }

  def contactPage = Action {
    Ok(views.html.contactPage())
    //Redirect(routes.HomeController.index())
  }
}
