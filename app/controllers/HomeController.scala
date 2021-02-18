package controllers

import dao.movieDAO
import models.Movies
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

  def contactPage = Action {
    Ok(views.html.contactPage())
    //Redirect(routes.HomeController.index())
  }

  def viewTen = Action async  {
    movieDAO.getReleasedMovies map {
      results => Ok(views.html.homePage(results))
    }
  }

  def viewAllUpcoming = Action async  {
    movieDAO.getUpcomingMovies map {
      results => Ok(views.html.homePage(results))
    }
  }
}
