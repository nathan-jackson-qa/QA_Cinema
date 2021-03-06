package dao

import models.CinemaMongo

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import models.JsonFormats.cinemaFormat
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.bson.{BSONDocument, BSONObjectID}
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection



class mongoCinemaDAO  @Inject()(implicit ec: ExecutionContext, reactiveMongoApi: ReactiveMongoApi){

    private def collection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection("cinemas"))

  def list(limit: Int = 100): Future[Seq[CinemaMongo]] =
    collection.flatMap(
      _.find(BSONDocument())
    .cursor[CinemaMongo](ReadPreference.Primary)
    .collect[Seq](limit, Cursor.FailOnError[Seq[CinemaMongo]]()))

  def create(cinema: CinemaMongo): Future[WriteResult] =
    collection.flatMap(_.insert(cinema))

  def read(id: BSONObjectID): Future[Option[CinemaMongo]] =
    collection.flatMap((_.find(BSONDocument("_id" -> id)).one[CinemaMongo]))

  def update(id: BSONObjectID, cinema: CinemaMongo): Future[Option[CinemaMongo]] =
    collection.flatMap(_.findAndUpdate(BSONDocument("_id" -> id), BSONDocument(f"$$set" -> BSONDocument(
      "name" -> cinema.name,
      "openingTimes" -> cinema.openingTimes,
      "address" -> cinema.address,
      "g_location" -> cinema.g_location,
      "parking" -> cinema.parking,
      "directions" -> cinema.directions,
      "img" -> cinema.img
  )
    ), true)
      .map(_.result[CinemaMongo]))
//
  def delete(id: BSONObjectID): Future[Option[CinemaMongo]] =
    collection.flatMap(_.findAndRemove(BSONDocument("_id" ->  id)).map(_.result[CinemaMongo]))

}
