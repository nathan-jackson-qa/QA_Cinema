package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class GalleryController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.whatsOn())
  }
}
