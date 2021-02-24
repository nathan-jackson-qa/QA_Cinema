package backend.Controllers

import controllers.ClassificationController
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class ClassificationControllerTest extends PlaySpec with Results {

  "Classification page #index" should {
    "be valid" in {
      val controller = new ClassificationController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.classification.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.Classifications())
    }
  }
}
