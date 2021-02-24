package backend.Controllers

import controllers.AboutController
import org.scalatestplus.play.PlaySpec
import play.api.mvc._
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class AboutControllerTest extends PlaySpec with Results {
  "About page #index" should {
    "be valid" in {
      val controller = new AboutController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.aboutPage())
    }
  }
}
