package controllers

import dao.{bookingDAO, cinemaDAO}
import models.{Booking, Cinema, bookingForm}
import play.api.mvc._

import java.time.{LocalDate, LocalTime}
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class BookingController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def form(id: Int, title: String, date: String, time: String) = Action.async {
        var newDate = LocalDate.parse(date)
        var newTime = LocalTime.parse(time)
    implicit request: Request[AnyContent] =>
      cinemaDAO.getAllCinemas.map {
        results => Ok(views.html.ticketBooking(bookingForm.form, results, id, title, newDate, newTime))
      }
  }

  def inputBooking() = Action { implicit request: Request[AnyContent] =>

    bookingForm.form.bindFromRequest().fold({ formWithErrors => BadRequest(views.html.ticketBooking(formWithErrors, Seq[Cinema](), 0, "", LocalDate.now, LocalTime.now)) },
      { widget =>
        val x = getPrice(widget)
        bookingDAO.add(x)
        Ok(views.html.testPayPal(x))
      })
  }

  def getPrice(b: Booking): Booking = {
    var total = 0.0
    if(b.deluxe){
      val adultPrice = 12.49
      val childPrice = 8.49
      total = (b.numOfAdult * adultPrice) + (b.numOfChild * childPrice) + b.concessions
    }
    else{
      val adultPrice = 9.49
      val childPrice = 6.49
      total = (b.numOfAdult * adultPrice) + (b.numOfChild * childPrice) + b.concessions
    }
    Booking(b.id, b.name_of_person, b.date, b.time, b.numOfAdult, b.numOfChild, b.deluxe, b.concessions, total, b.movie_id, b.cinema_id)
  }
}