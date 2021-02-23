package backend.Controllers

import controllers.DiscussionController
import dao.discussionDAO
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DiscussionControllerTest extends PlaySpec with Results {

  "Discussion page #index" should {
    "be valid" in {
      val controller = new DiscussionController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(discussionDAO.getReviews map {
        results => Ok(views.html.discussionPage(results))
      })
    }
  }

//  "Discussion page #discForm" should {
//    "be valid" in {
//      val controller = new DiscussionController(Helpers.stubControllerComponents())
//      val result: Future[Result] = controller.discForm().apply(FakeRequest())
//      val bodyText = contentAsString(result)
//      bodyText mustBe contentAsString(views.html.discussionPage2((discussionForm.form)(implicit messages: Messages))
//      })
//    }
//  }
}
