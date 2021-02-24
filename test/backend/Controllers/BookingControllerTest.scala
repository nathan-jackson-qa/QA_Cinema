package backend.Controllers

import controllers.BookingController
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}

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
}
