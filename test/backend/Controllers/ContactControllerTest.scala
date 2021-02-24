package backend.Controllers

import controllers.ContactController
import org.jvnet.mock_javamail.MockTransport
import org.scalatestplus.play.PlaySpec
import play.api.mvc.{Result, Results}
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import java.util.Properties
import javax.mail.Provider
import scala.concurrent.Future

class MockedSMTPProvider extends Provider(Provider.Type.TRANSPORT, "mocked", classOf[MockTransport].getName, "Mock", null)

class ContactControllerTest extends PlaySpec with Results {

  "Contact page #contactPage" should {
    "be valid" in {
      val controller = new ContactController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.contactPage.apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.contactPage())
    }
  }

  private val mockedSession = javax.mail.Session.getDefaultInstance(new Properties() {{
    put("mail.transport.protocol.rfc822", "mocked")
  }})
  mockedSession.setProvider(new MockedSMTPProvider)

//  def index(email: String, subject: String, message: String) = Action {
//    mailer(Envelope.from("teamearth137" `@`  "gmail.com")
//      .to("teamearth137" `@` "gmail.com")
//      .subject(subject)
//      .content(Text(message + " " + "Sent From: " + email))).onComplete {
//      case Success(value) =>
//        println("message delivered")
//      case Failure(exception) =>
//        println("message failed")
//        exception.printStackTrace()
//    }
//    Ok(views.html.contactPage())
//  }

  "Contact page #index" should {
    "send an email" in {
      val controller = new ContactController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.index("teamearth137@gmail.com", "test subject" , "test message").apply(FakeRequest())
      val bodyText = contentAsString(result)
      bodyText mustBe contentAsString(views.html.contactPage())
//      val mailer = Mailer(mockedSession)
//      val future = mailer(Envelope.from("teamearth137" `@`  "gmail.com")
//        .to("teamearth137" `@` "gmail.com")
//        .subject("tes subject")
//        .content(Text("test message")))

//      Await.ready(future, 5.seconds)
//      val testInbox = Mailbox.get("teamearth137@gmail.com")
//      testInbox.size === 1
//      val testMsg = testInbox.get(0)
//      testMsg.getContent === "team earth"
//      testMsg.getSubject === "test subject"
    }
  }
}
