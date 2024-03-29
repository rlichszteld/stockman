package com.rlsoft.stockman.models.id

import cats.syntax.either._
import io.circe.{Decoder, Encoder}
import org.bson.types.ObjectId

trait ObjectIdSupport extends IdProvider[ObjectId] {
  override def generateId: ObjectId = new ObjectId

  implicit val encodeObjectId: Encoder[ObjectId] = Encoder.encodeString.contramap(_.toString)

  implicit val decodeObjectId: Decoder[ObjectId] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(new ObjectId(str)).leftMap(x => x.getMessage)
  }
}

object ObjectIdSupport extends ObjectIdSupport
