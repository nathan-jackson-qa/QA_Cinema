package controllers

import dao.mongoBookingDAO
import models.{Booking, BookingData, BookingMongo, Cinema, bookingForm, bookingMongoForm}
import models.JsonFormats.bookingFormat
import play.api.http.Writeable.wBytes
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.bson.BSONObjectID
import reactivemongo.play.json.collection.JSONCollection

import java.time.{LocalDate, LocalTime}
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class BookingMongoController @Inject()(components: ControllerComponents, val reactiveMongoApi: ReactiveMongoApi, dao: mongoBookingDAO) extends AbstractController(components)
  with MongoController with ReactiveMongoComponents with play.api.i18n.I18nSupport {
  implicit def ec: ExecutionContext = components.executionContext

  def listBookings = Action async {
    dao.list(100).map {bookings =>
      Ok(Json.toJson(bookings))}
  }

  def create = Action { implicit request: Request[AnyContent] =>
    bookingMongoForm.form.bindFromRequest().fold({ formWithErrors => BadRequest(views.html.mongoTicketBooking) },
      { widget =>
        val x = getPrice(widget)
        dao.create(x)
        Ok(Json.toJson(x))
      })
  }

  def getPrice(b: BookingData): BookingMongo = {
    var total = 0.0
    if(b.deluxe){
      val adultPrice = 12.49
      val childPrice = 8.49
      total = (b.numOfAdult * adultPrice) + (b.numOfChildren * childPrice) + b.concessions
    }
    else{
      val adultPrice = 9.49
      val childPrice = 6.49
      total = (b.numOfAdult * adultPrice) + (b.numOfChildren * childPrice) + b.concessions
    }
    BookingMongo(b.name, b.date, b.time, b.numOfAdult, b.numOfChildren, b.deluxe, b.concessions, total, b.movieID, b.cinemaID)
  }
  def read(id: BSONObjectID) = Action.async {
    dao.read(id).map { maybeBooking =>
      maybeBooking.map { result =>
        Ok(Json.toJson((result)))
      }.getOrElse((NotFound))
    }
  }

  def update(id: BSONObjectID) = Action.async(parse.json) {
    _.body.validate[BookingMongo].map { result =>
      dao.update(id, result).map {
        case Some(feed) =>Ok(Json.toJson(feed))
        case _ => NotFound
      }
    }.getOrElse(Future.successful(BadRequest("Invalid update")))
  }

  def delete(id: BSONObjectID) = Action async {
    dao.delete(id).map {
      case Some(booking) => Ok(Json.toJson(booking))
      case _ => NotFound
    }
  }
}
