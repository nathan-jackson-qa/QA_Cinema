package controllers

import dao.movieDAO
import models.Movie
import play.api.mvc._

import javax.inject._
import play.api.mvc.{AbstractController, Action, ControllerComponents}

import java.time.LocalDate
import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

class GalleryController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def outNow = Action async  {
    movieDAO.getCurrentMovies map {
      results => Ok(views.html.whatsOn(results, "Out Now"))
    }
  }

  def comingSoon = Action async  {
    movieDAO.getUpcomingMovies map {
      results => Ok(views.html.whatsOn(results, "Coming Soon"))
    }
  }

  def showMovieInfo(id: Int) = Action async {
    val startDate = LocalDate.now
    movieDAO.getMovieActors(id).flatMap { actors =>
      movieDAO.getMovieDetails(id).map {
        case Some(movie: Movie) => Ok(views.html.individualWhatsOn(actors, movie, startDate))
        case None => Ok(views.html.contactPage(" "))
      }

    }
  }
}
