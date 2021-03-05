package controllers

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json._
import reactivemongo.api.Cursor
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.play.json._
import collection._
import models._
import models.JsonFormats._
import dao.mongoCinemaDAO
import reactivemongo.bson.BSONObjectID

class NotMongoController @Inject() (components: ControllerComponents,val reactiveMongoApi: ReactiveMongoApi, dao: mongoCinemaDAO) extends AbstractController(components)
  with MongoController with ReactiveMongoComponents {

  implicit def ec: ExecutionContext = components.executionContext

  def cinemaCollection: Future[JSONCollection] = database.map(_.collection[JSONCollection]("cinemas"))

//  def createCinema = Action.async {
//    val cinema = CinemaMongo("My Cinema", "01:00-09:00", "3 New Street", "google", "parking", "One left, one right", "me.jpg")
//
//    // insert the user
//    val futureResult = cinemaCollection.flatMap(_.insert.one(cinema))
//
//    // when the insert is performed, send a OK 200 result
//    futureResult.map(_ => Ok)
//  }

//  def findCinema(name: String) = Action.async {
//    // let's do our query
//    val cursor: Future[Cursor[CinemaMongo]] = cinemaCollection.map {
//      // find all people with name `name`
//      _.find(Json.obj("name" -> name)).
//        cursor[CinemaMongo]()
//    }
//
//    // gather all the JsObjects in a list
//    val futureCinemasList: Future[List[CinemaMongo]] =
//      cursor.flatMap(_.collect[List](-1, Cursor.FailOnError[List[CinemaMongo]]()))
//
//    // everything's ok! Let's reply with the array
//    futureCinemasList.map { cinemas =>
//      Ok(cinemas.toString)
//    }
//  }


  def listCinemas = Action async {
    dao.list(100).map {cinemas =>
      Ok(Json.toJson(cinemas))}
  }

  def create = Action.async(parse.json) {
    _.body.validate[CinemaMongo].map { result =>
      dao.create(result).map { _ =>
        Created}
    }.getOrElse(Future.successful(BadRequest("Invalid cinema")))
    }

  def read(id: BSONObjectID) = Action.async {
    dao.read(id).map { maybeCinema =>
      maybeCinema.map { result =>
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

