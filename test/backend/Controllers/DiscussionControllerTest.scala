package backend.Controllers

import controllers.DiscussionController
import dao.discussionDAO
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}

import scala.concurrent.Future

class DiscussionControllerTest extends PlaySpec with Results {

  discussionDAO.getReviews
  "Contact page #contactPage" should {
    "be valid" in {
      val controller = new DiscussionController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index.apply(FakeRequest())
      val bodyText = contentAsString(result)
      //bodyText mustBe views.html.discussionPage(reviews)
    }
  }
}
