package backend.Controllers

import controllers.BookingController
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.mvc.{Result, Results}
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers.{POST, contentAsString, defaultAwaitTimeout}

import scala.concurrent.Future

class BookingControllerTest extends PlaySpec with Results {

  val controller = new BookingController(Helpers.stubControllerComponents())

  "Booking page #form" should {
    "be valid" in {
      val result: Future[Result] = controller.form(1, "Shrek", "2021-10-21", "21:00").apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText should include ("<h1 class=\"card-title\" style=\"color: white\">You are now booking tickets for Shrek</h1>")
    }
  }

  "Booking page #inputBooking" should {
    "be valid (with deluxe)" in {
      val bookingJson =
        Json.obj("id" -> 1, "name_of_person" -> "Test", "date" -> "2021-10-21", "time" -> "21:00", "numOfAdult" -> 2, "numOfChild" -> 1, "deluxe" -> true, "concessions" -> 1.99, "total" -> 10, "movie_id" -> 1, "cinema_id" -> 1)
      val result: Future[Result] = controller.inputBooking().apply(FakeRequest(POST, "Booking").withJsonBody(bookingJson))
      val bodyText = contentAsString(result)
      bodyText should include ("Confirm the details below then proceed to Paypal Payment when ready")
    }
  }

  "Booking page #inputBooking" should {
    "be valid (without deluxe)" in {
      val bookingJson =
        Json.obj("id" -> 1, "name_of_person" -> "Test", "date" -> "2021-10-21", "time" -> "21:00", "numOfAdult" -> 2, "numOfChild" -> 1, "deluxe" -> false, "concessions" -> 1.99, "total" -> 10, "movie_id" -> 1, "cinema_id" -> 1)
      val result: Future[Result] = controller.inputBooking().apply(FakeRequest(POST, "Booking").withJsonBody(bookingJson))
      val bodyText = contentAsString(result)
      bodyText should include ("Confirm the details below then proceed to Paypal Payment when ready")
    }
  }
}
