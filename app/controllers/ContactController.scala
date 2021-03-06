package controllers.mysql

import courier._
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

@Singleton
class ContactController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def contactPage = Action {
    Ok(views.html.mysql.contactPage(" "))
    //Redirect(routes.HomeController.index())
  }

  val mailer = Mailer("smtp.gmail.com", 587)
    .auth(true)
    .as("teamearth137@gmail.com", "teamEarth123??")
    .startTls(true)()


  def index(email: String, subject: String, message: String) = Action {
    mailer(Envelope.from("teamearth137" `@`  "gmail.com")
      .to("teamearth137" `@` "gmail.com")
      .subject(subject)
      .content(Text(message + " " + "Sent From: " + email))).onComplete {
      case Success(value) =>
        println("message delivered")
      case Failure(exception) =>
        println("message failed")
        exception.printStackTrace()
    }
    Ok(views.html.mysql.contactPage("Successful!!"))
  }
}
