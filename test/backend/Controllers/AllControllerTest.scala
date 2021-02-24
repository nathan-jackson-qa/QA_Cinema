package backend.Controllers

import controllers.{AboutController, BookingController, ClassificationController, ContactController, DiscussionController, GalleryController, GettingHereController, HomeController, OpeningTimesController, ScreenController, placesToGoController}
import dao.{cinemaDAO, discussionDAO, movieDAO, venuesDAO}
import org.scalatest.matchers.should.Matchers._
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.mvc.{Result, Results}
import play.api.test.{FakeRequest, Helpers}
import play.api.test.Helpers.{POST, contentAsString, defaultAwaitTimeout}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class AllControllerTest extends PlaySpec with Results {

  "About page #index" should {
    "be valid" in {
      val controller = new AboutController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.aboutPage())
    }
  }

  "Booking page #form" should {
    "be valid" in {
      val controller = new BookingController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.form(1, "Shrek", "2021-10-21", "21:00").apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText should include ("<h1 class=\"card-title\" style=\"color: white\">You are now booking tickets for Shrek</h1>")
    }
  }

  "Booking page #inputBooking" should {
    "be valid (with deluxe)" in {
      val controller = new BookingController(Helpers.stubControllerComponents())
      val bookingJson =
        Json.obj("id" -> 1, "name_of_person" -> "Test", "date" -> "2021-10-21", "time" -> "21:00", "numOfAdult" -> 2, "numOfChild" -> 1, "deluxe" -> true, "concessions" -> 1.99, "total" -> 10, "movie_id" -> 1, "cinema_id" -> 1)
      val result: Future[Result] = controller.inputBooking().apply(FakeRequest(POST, "Booking").withJsonBody(bookingJson))
      val bodyText = contentAsString(result)
      bodyText should include ("Confirm the details below then proceed to Paypal Payment when ready")
    }
  }

  "Booking page #inputBooking" should {
    "be valid (without deluxe)" in {
      val controller = new BookingController(Helpers.stubControllerComponents())
      val bookingJson =
        Json.obj("id" -> 1, "name_of_person" -> "Test", "date" -> "2021-10-21", "time" -> "21:00", "numOfAdult" -> 2, "numOfChild" -> 1, "deluxe" -> false, "concessions" -> 1.99, "total" -> 10, "movie_id" -> 1, "cinema_id" -> 1)
      val result: Future[Result] = controller.inputBooking().apply(FakeRequest(POST, "Booking").withJsonBody(bookingJson))
      val bodyText = contentAsString(result)
      bodyText should include ("Confirm the details below then proceed to Paypal Payment when ready")
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
      bodyText mustBe contentAsString(views.html.contactPage(" "))
    }
  }

  "Contact page #index" should {
    "send an email" in {
      val controller = new ContactController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index("teamearth137@gmail.com", "test subject", "test message").apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.contactPage("Successful!!"))
    }
  }

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

  "Discussion page #discForm" should {
    "be valid" in {
      val controller = new DiscussionController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.discForm().apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText should include ("<h1 class=\"card-title\" style=\"color: white\">Welcome to our discussion board movie lovers!</h1>")
    }
  }

  "Discussion page #discPost" should {
    "be valid" in {
      val controller = new DiscussionController(Helpers.stubControllerComponents())
      val discussionJson = Json.obj("id" -> 1, "title" -> "shrek", "description" -> "test description", "rating" -> "10", "onApproved" -> false)
      val result: Future[Result] = controller.discFormPost().apply(FakeRequest(POST, "discussions/entry").withJsonBody(discussionJson))
      val bodyText = contentAsString(result)
      bodyText should include ("<h1 class=\"card-title\" style=\"color: white\">Your review was: SUCCESSFUL</h1>")
    }
  }

  "Discussion page #discPost" should {
    "be valid (with profanity)" in {
      val controller = new DiscussionController(Helpers.stubControllerComponents())
      val discussionJson = Json.obj("id" -> 1, "title" -> "shrek", "description" -> "wh00r description", "rating" -> "10", "onApproved" -> false)
      val result: Future[Result] = controller.discFormPost().apply(FakeRequest(POST, "discussions/entry").withJsonBody(discussionJson))
      val bodyText = contentAsString(result)
      bodyText should include ("<h1 class=\"card-title\" style=\"color: white\">Your review was: UNSUCCESSFUL</h1>")
    }
  }


  "Gallery page #outNow" should {
    "be valid" in {
      val controller = new GalleryController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.outNow.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(movieDAO.getCurrentMovies map {
        results => Ok(views.html.whatsOn(results, "Out Now"))
      })
    }
  }

  "Gallery page #comingSoon" should {
    "be valid" in {
      val controller = new GalleryController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.comingSoon.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(movieDAO.getUpcomingMovies map {
        results => Ok(views.html.whatsOn(results, "Coming Soon"))
      })
    }
  }

  "Gallery page #showMovieInfo" should {
    "be valid" in {
      val controller = new GalleryController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.showMovieInfo(1).apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText should include ("Shrek, an ogre, embarks on a journey with a donkey to rescue Princess Fiona from a vile lord and regain his swamp.")
    }
  }

  "Gallery page #showMovieInfo" should {
    "be valid (invalid value)" in {
      val controller = new GalleryController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.showMovieInfo(6000000).apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText should include ("QA Cinemas : Contact Page")
    }
  }

  "Getting Here page #index" should {
    "be valid" in {
      val controller = new GettingHereController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(cinemaDAO.getAllCinemas map {
        cinemaDetails => Ok(views.html.gettingHere(cinemaDetails))
      })
    }
  }

  "Home page #viewAll " should {
    "be valid" in {
      val controller = new HomeController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.viewAll .apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(movieDAO.getAllMovies map {
        results => Ok(views.html.homePage(results))
      })
    }
  }

  "Home page #search" should {
    "be valid" in {
      val controller = new HomeController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.search("shrek").apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText should include ("Shrek, an ogre, embarks on a journey with a donkey to rescue Princess Fiona from a vile lord and regain his swamp.")
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

  "Places To Go page #showCinemaVenue" should {
    "be valid" in {
      val controller = new placesToGoController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.showCinemaVenue(1).apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(venuesDAO.getAllVenuesById(1) map {
        selectedResults => Ok(views.html.placesToGo2(selectedResults))
      })
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
