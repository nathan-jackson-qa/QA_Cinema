package dao

import models.JsonFormats.bookingFormat
import models.BookingMongo
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.api.{Cursor, ReadPreference}
import reactivemongo.bson.{BSONDocument, BSONObjectID}
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


class mongoBookingDAO  @Inject()(implicit ec: ExecutionContext, reactiveMongoApi: ReactiveMongoApi){

    private def collection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection("bookings"))

  def list(limit: Int = 100): Future[Seq[BookingMongo]] =
    collection.flatMap(
      _.find(BSONDocument())
    .cursor[BookingMongo](ReadPreference.Primary)
    .collect[Seq](limit, Cursor.FailOnError[Seq[BookingMongo]]()))

  def create(booking: BookingMongo): Future[WriteResult] =
    collection.flatMap(_.insert(booking))

  def read(id: BSONObjectID): Future[Option[BookingMongo]] =
    collection.flatMap((_.find(BSONDocument("_id" -> id)).one[BookingMongo]))

  def update(id: BSONObjectID, booking: BookingMongo): Future[Option[BookingMongo]] =
    collection.flatMap(_.findAndUpdate(BSONDocument("_id" -> id), BSONDocument(f"$$set" -> BSONDocument(
      "_id" -> booking._id,
      "name" -> booking.name,
      "date" -> booking.date,
      "time" -> booking.time,
      "numOfAdult" -> booking.numOfAdult,
      "numOfChildren" -> booking.numOfChildren,
      "deluxe" -> booking.deluxe,
      "concessions" -> booking.concessions,
      "total" -> booking.total,
      "movieID" -> booking.movieID,
      "cinemaID" -> booking.cinemaID

  )
    ), true)
      .map(_.result[BookingMongo]))
//
  def delete(id: BSONObjectID): Future[Option[BookingMongo]] =
    collection.flatMap(_.findAndRemove(BSONDocument("_id" ->  id)).map(_.result[BookingMongo]))

}
