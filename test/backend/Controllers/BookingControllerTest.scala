package backend.Controllers

import controllers.BookingController
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Results
import play.api.test.Helpers

class BookingControllerTest extends PlaySpec with Results {

  val controller = new BookingController(Helpers.stubControllerComponents())


//  "About page #index" should {
//    "be valid" in {
//      val result: Future[Result] = controller.inputBooking().apply(FakeRequest())
//      val bodyText = contentAsString(result)
//      bodyText mustBe views.html.ticketBooking
//    }
//  }
}
