package backend.Controllers

import controllers.mysql.placesToGoController
import dao.venuesDAO
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class PlacesToGoControllerTest  extends PlaySpec with Results {

  "Places To Go page #showCinemaVenue" should {
    "be valid" in {
      val controller = new placesToGoController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.showCinemaVenue(1).apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(venuesDAO.getAllVenuesById(1) map {
        selectedResults => Ok(views.html.placesToGo2(selectedResults))
      })
    }
  }
}
