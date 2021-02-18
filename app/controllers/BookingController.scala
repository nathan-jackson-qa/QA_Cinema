package controllers

import dao.{bookingDAO, cinemaDAO, movieDAO}
import models.{Cinema, bookingForm}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}

@Singleton
class BookingController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport{

  def form(id: Int) = Action.async{
    implicit request: Request[AnyContent] =>
      cinemaDAO.getAllCinemas.map{
        results => Ok(views.html.ticketBooking(bookingForm.form, results, id))
      }
  }

  def inputBooking() = Action { implicit request: Request[AnyContent] =>
    bookingForm.form.bindFromRequest().fold({ formWithErrors => BadRequest(views.html.ticketBooking(formWithErrors, Seq[Cinema](),0))},
      { widget => bookingDAO.add(widget)
        Ok(views.html.testPayPal(widget))
    })
  }
}
