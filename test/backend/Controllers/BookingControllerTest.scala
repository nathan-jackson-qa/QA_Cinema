package backend.Controllers

import controllers.BookingController
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class BookingControllerTest extends PlaySpec with Results {

  val controller = new BookingController(Helpers.stubControllerComponents())

  "About page #index" should {
    "be valid" in {
      val result: Future[Result] = controller.inputBooking().apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe views.html.ticketBooking
    }
  }
}
