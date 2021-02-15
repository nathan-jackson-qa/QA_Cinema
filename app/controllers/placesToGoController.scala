package controllers
import dao.VenuesDAO
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

class placesToGoController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {

  def viewAll = Action async {
    VenuesDAO.all map {
      itemList => Ok(views.html.placesToGo(itemList))
    }
  }
}
