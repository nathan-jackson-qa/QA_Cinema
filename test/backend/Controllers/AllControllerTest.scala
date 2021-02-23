package backend.Controllers

import controllers.{AboutController, ClassificationController, ContactController, OpeningTimesController, ScreenController}
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}

import scala.concurrent.Future

class AllControllerTest extends PlaySpec with Results {
  "About page #index" should {
    "be valid" in {
      val controller = new AboutController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.aboutPage())
    }
  }

  "Classification page #index" should {
    "be valid" in {
      val controller = new ClassificationController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.classification.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.Classifications())
    }
  }

  "Contact page #contactPage" should {
    "be valid" in {
      val controller = new ContactController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.contactPage.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.contactPage())
    }
  }

  "Opening Times page #index" should {
    "be valid" in {
      val controller = new OpeningTimesController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.openingTimesPage())
    }
  }

  "Screen Times page #index" should {
    "be valid" in {
      val controller = new ScreenController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.screen.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.screenPage())
    }
  }
}
