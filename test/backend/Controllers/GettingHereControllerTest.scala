package backend.Controllers

import controllers.GettingHereController
import dao.cinemaDAO
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class GettingHereControllerTest extends PlaySpec with Results {

  val controller = new GettingHereController(Helpers.stubControllerComponents())

  "Getting Here page #index" should {
    "be valid" in {
      val result: Future[Result] = controller.index.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(cinemaDAO.getAllCinemas map {
        cinemaDetails => Ok(views.html.gettingHere(cinemaDetails))
      })
    }
  }
}
