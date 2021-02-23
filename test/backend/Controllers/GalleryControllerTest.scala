package backend.Controllers

import controllers.GalleryController
import dao.movieDAO
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class GalleryControllerTest extends PlaySpec with Results {

  val controller = new GalleryController(Helpers.stubControllerComponents())

  "Gallery page #outNow" should {
    "be valid" in {
      val result: Future[Result] = controller.outNow.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(movieDAO.getCurrentMovies map {
        results => Ok(views.html.whatsOn(results))
      })
    }
  }

  "Gallery page #index" should {
    "be valid" in {
      val result: Future[Result] = controller.comingSoon.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(movieDAO.getUpcomingMovies map {
        results => Ok(views.html.whatsOn(results))
      })
    }
  }
}
