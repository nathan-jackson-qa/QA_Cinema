package controllers
import dao.cinemaDAO
import play.api.i18n.I18nSupport
import play.api.libs.concurrent.ExecutionContextProvider
import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject._
import play.api.mvc._

@Singleton
class GettingHereController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport
{
  def index = Action.async { implicit request =>
    cinemaDAO.getAllCinemas map {
      cinemaDetails => Ok(views.html.gettingHere(cinemaDetails))
    } recover {
      case error: Exception => InternalServerError("Could not retrieve cinema details")
    }
  }
}
