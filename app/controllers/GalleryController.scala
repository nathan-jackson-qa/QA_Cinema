package controllers

import dao.movieDAO
import play.api.mvc._

import javax.inject._
import play.api.mvc.{AbstractController, Action, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global


@Singleton
class GalleryController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def viewAll = Action async  {
    movieDAO.getAllMovies map {
      results => Ok(views.html.whatsOn(results))
    }
  }
}
