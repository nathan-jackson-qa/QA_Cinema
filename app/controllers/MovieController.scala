package controllers

import dao.mongoMovieDAO
import models.JsonFormats.movieFormat
import models.MovieMongo
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.bson.BSONObjectID
import reactivemongo.play.json.collection.JSONCollection

import java.time.LocalDate
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class MovieController @Inject()(components: ControllerComponents, val reactiveMongoApi: ReactiveMongoApi, dao: mongoMovieDAO) extends AbstractController(components)
  with MongoController with ReactiveMongoComponents {

  implicit def ec: ExecutionContext = components.executionContext

  def movieCollection: Future[JSONCollection] = database.map(_.collection[JSONCollection]("movies"))


  def listMovies = Action async {
    dao.list(100).map {movies =>
      Ok(views.html.mongoHome(movies))}
  }

  def listings(id: Int) = Action async {
    val startDate = LocalDate.now
    dao.list(100).map {movies =>
      Ok(views.html.mongoListing(movies(id), startDate))}
  }

  def create = Action.async(parse.json) {
    _.body.validate[MovieMongo].map { result =>
      dao.create(result).map { _ =>
        Created}
    }.getOrElse(Future.successful(BadRequest("Invalid movie")))
  }

  def read(id: BSONObjectID) = Action.async {
    dao.read(id).map { maybeMovie =>
      maybeMovie.map { result =>
        Ok(Json.toJson((result)))
      }.getOrElse((NotFound))
    }
  }

  def update(id: BSONObjectID) = Action.async(parse.json) {
    _.body.validate[MovieMongo].map { result =>
      dao.update(id, result).map {
        case Some(feed) =>Ok(Json.toJson(feed))
        case _ => NotFound
      }
    }.getOrElse(Future.successful(BadRequest("Invalid update")))
  }

  def delete(id: BSONObjectID) = Action async {
    dao.delete(id).map {
      case Some(movie) => Ok(Json.toJson(movie))
      case _ => NotFound
    }
  }
}

