package controllers

import dao.movieDAO
import models.Movies
import play.api.mvc.Results.Ok
import play.api.mvc._

import javax.inject._
import play.api.mvc.{AbstractController, Action, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def viewAll = Action async  {
    movieDAO.getAllMovies map {
      results => Ok(views.html.homePage(results))
    }
  }

  def search(keyword: String) = Action async {
    movieDAO.searchBykeyword(keyword) map {
      results => Ok(views.html.searchResults(results))
    }
  }
}
