package controllers.mysql

import dao.movieDAO
import models.Movie
import play.api.mvc.{AbstractController, ControllerComponents}

import java.time.LocalDate
import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

class GalleryController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def outNow = Action async  {
    movieDAO.getCurrentMovies map {
      results => Ok(views.html.mysql.whatsOn(results, "Out Now"))
    }
  }

  def comingSoon = Action async  {
    movieDAO.getUpcomingMovies map {
      results => Ok(views.html.mysql.whatsOn(results, "Coming Soon"))
    }
  }

  def showMovieInfo(id: Int) = Action async {
    val startDate = LocalDate.now
    movieDAO.getMovieActors(id).flatMap { actors =>
      movieDAO.getMovieDetails(id).map {
        case Some(movie: Movie) => Ok(views.html.mysql.individualWhatsOn(actors, movie, startDate))
        case None => Ok(views.html.mysql.contactPage(" "))
      }

    }
  }
}
