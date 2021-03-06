package controllers

import dao.mongoCinemaDAO
import models.JsonFormats._
import models.{CinemaMongo, _}
import play.api.libs.json._
import play.api.mvc.{AbstractController, ControllerComponents}
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.bson.BSONObjectID
import reactivemongo.play.json.collection._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CinemaController @Inject()(components: ControllerComponents, val reactiveMongoApi: ReactiveMongoApi, dao: mongoCinemaDAO) extends AbstractController(components)
  with MongoController with ReactiveMongoComponents {

  implicit def ec: ExecutionContext = components.executionContext

  def cinemaCollection: Future[JSONCollection] = database.map(_.collection[JSONCollection]("cinemas"))


  def listCinemas = Action async {
    dao.list(100).map {cinemas =>
      Ok(views.html.gettingHereMongo(cinemas))}
  }

  def create = Action.async(parse.json) {
    _.body.validate[CinemaMongo].map { result =>
      dao.create(result).map { _ =>
        Created}
    }.getOrElse(Future.successful(BadRequest("Invalid cinema")))
    }

  def read(id: BSONObjectID) = Action.async {
    dao.read(id).map { maybeCinema =>
      maybeCinema.map { result => println("Address " + result.address + " Name: " + result.name);
        Ok(Json.toJson((result)))
      }.getOrElse((NotFound))
    }
  }

  def update(id: BSONObjectID) = Action.async(parse.json) {
    _.body.validate[CinemaMongo].map { result =>
      dao.update(id, result).map {
        case Some(feed) =>Ok(Json.toJson(feed))
        case _ => NotFound
      }
    }.getOrElse(Future.successful(BadRequest("Invalid update")))
  }

  def delete(id: BSONObjectID) = Action async {
    dao.delete(id).map {
      case Some(cinema) => Ok(Json.toJson(cinema))
      case _ => NotFound
    }
  }
}

