package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class AboutController @Inject()(cc: ControllerComponents) extends AbstractController(cc)
{
  def index = Action {
    Ok(views.html.aboutPage())
  }
}
