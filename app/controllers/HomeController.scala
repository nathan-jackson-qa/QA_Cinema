package controllers

import dao.movieDAO
import models.Movie
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.collection.mutable.Set
import scala.concurrent.ExecutionContext.Implicits.global

class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def viewAll = Action async {
    movieDAO.getAllMovies map {
      results => Ok(views.html.homePage(results))
    }
  }

  def search(keyword: String) = Action async {
    var words = keyword.split(" ")
    var movies: Set[Movie] = Set()
    for (word <- words) {
      movieDAO.searchActors(word) map {
        results => {
          movies ++= results
        }
      }
    }
    movieDAO.searchBykeyword(keyword) map {
      secondResults => {
        var results = movies ++ secondResults.toSet;
        Ok(views.html.searchResults(results.toSeq))
      }
    }
  }

}
