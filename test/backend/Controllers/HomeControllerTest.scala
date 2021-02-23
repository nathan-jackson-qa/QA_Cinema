package backend.Controllers

import controllers.HomeController
import dao.movieDAO
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class HomeControllerTest  extends PlaySpec with Results {

  val controller = new HomeController(Helpers.stubControllerComponents())

  "Home page #viewAll " should {
    "be valid" in {
      val result: Future[Result] = controller.viewAll .apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(movieDAO.getAllMovies map {
        results => Ok(views.html.homePage(results))
      })
    }
  }
}
