package backend.Controllers

import controllers.mysql.OpeningTimesController
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}

import scala.concurrent.Future

class OpeningTimesControllerTest extends PlaySpec with Results {

  "Opening Times page #index" should {
    "be valid" in {
      val controller = new OpeningTimesController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.openingTimesPage())
    }
  }
}
