package backend.Controllers

import controllers.GalleryController
import dao.movieDAO
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.mvc._
import play.api.test.Helpers._
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class GalleryControllerTest extends PlaySpec with Results with GuiceOneAppPerSuite {

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

  "Gallery page #comingSoon" should {
    "be valid" in {
      val result: Future[Result] = controller.comingSoon.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(movieDAO.getUpcomingMovies map {
        results => Ok(views.html.whatsOn(results))
      })
    }
  }

  "Gallery page #showMovieInfo" should {
    "be valid" in {
      val result: Future[Result] = controller.showMovieInfo(1).apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText should include ("Shrek, an ogre, embarks on a journey with a donkey to rescue Princess Fiona from a vile lord and regain his swamp.")
    }
  }
}
