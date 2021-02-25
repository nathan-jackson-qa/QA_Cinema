package backend.Controllers

import controllers.ContactController
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class ContactControllerTest extends PlaySpec with Results {

  "Contact page #contactPage" should {
    "be valid" in {
      val controller = new ContactController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.contactPage.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.contactPage(" "))
    }
  }

  "Contact page #index" should {
    "send an email" in {
      val controller = new ContactController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index("teamearth137@gmail.com", "test subject" , "test message").apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.contactPage("Successful!!"))
    }
  }
}
