package dao

import models.{CinemaMongo, MovieMongo}
import models.JsonFormats.movieFormat
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.bson.{BSONDocument, BSONObjectID}
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


class mongoMovieDAO  @Inject()(implicit ec: ExecutionContext, reactiveMongoApi: ReactiveMongoApi){

    private def collection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection("movies"))

  def list(limit: Int = 100): Future[Seq[MovieMongo]] =
    collection.flatMap(
      _.find(BSONDocument())
    .cursor[MovieMongo](ReadPreference.Primary)
    .collect[Seq](limit, Cursor.FailOnError[Seq[MovieMongo]]()))

  def create(movie: MovieMongo): Future[WriteResult] =
    collection.flatMap(_.insert(movie))

  def read(id: BSONObjectID): Future[Option[MovieMongo]] =
    collection.flatMap((_.find(BSONDocument("_id" -> id)).one[MovieMongo]))

  def update(id: BSONObjectID, movie: MovieMongo): Future[Option[MovieMongo]] =
    collection.flatMap(_.findAndUpdate(BSONDocument("_id" -> id), BSONDocument(f"$$set" -> BSONDocument(
      "title" -> movie.title,
      "desc" -> movie.desc,
      "director" -> movie.director,
      "actors" -> movie.actors,
      "img" -> movie.img,
      "classification" -> movie.classification,
      "isReleased" -> movie.isReleased
  )
    ), true)
      .map(_.result[MovieMongo]))
//
  def delete(id: BSONObjectID): Future[Option[MovieMongo]] =
    collection.flatMap(_.findAndRemove(BSONDocument("_id" ->  id)).map(_.result[MovieMongo]))

}
