package backend.Controllers

import controllers.ScreenController
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}

import scala.concurrent.Future

class ScreenControllerTest  extends PlaySpec with Results {

  "Opening Times page #index" should {
    "be valid" in {
      val controller = new ScreenController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.screen.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.screenPage())
    }
  }
}
