package controllers

import dao.{cinemaDAO, venuesDAO}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Action, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

class placesToGoController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  def viewAll = Action async {
    cinemaDAO.getAllCinemas map {
      results => Ok(views.html.placesToGo(results))
    }
  }

  def showCinemaVenue(id: Int) = Action async {
    venuesDAO.getAllVenuesById(id) map {
      selectedResults => Ok(views.html.placesToGo2(selectedResults))
    }
  }
}
