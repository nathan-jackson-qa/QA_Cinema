package controllers

import dao.bookingDAO
import models.bookingForm
import play.api.mvc._

import javax.inject.{Inject, Singleton}

@Singleton
class BookingController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport{

  def form() = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.ticketBooking(bookingForm.form))
  }

  def inputBooking() = Action { implicit request: Request[AnyContent] =>
    println(bookingForm.form.bindFromRequest().get)
    bookingForm.form.bindFromRequest().fold({ formWithErrors => BadRequest(views.html.ticketBooking(formWithErrors))},
      { widget => bookingDAO.add(widget)
        Redirect(routes.PayPalController.index())
    })
  }
}
