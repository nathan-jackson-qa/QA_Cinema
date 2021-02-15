package controllers

import javax.inject._
import play.api.mvc._
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ClassificationController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def classification = Action {
    Ok(views.html.Classifications())
  }
}
