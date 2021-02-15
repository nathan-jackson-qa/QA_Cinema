package controllers

import play.api.i18n.Messages.implicitMessagesProviderToMessages

import javax.inject._
import play.api.mvc._

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

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
