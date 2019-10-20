package com.rlsoft.stockman.persistence.mongodb

import com.mongodb.BasicDBObject
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.bson.conversions.Bson
import org.mongodb.scala.result.UpdateResult
import org.mongodb.scala.{Completed, MongoCollection, MongoDatabase}

import scala.concurrent.Future

class PortfolioRepository(val db: MongoDatabase) {

  private val collection: MongoCollection[PortfolioDocument] = db.getCollection[PortfolioDocument]("portfolios")

  private def byId(id: ObjectId): Bson = new BasicDBObject("_id", id)

  def getAll: Future[Seq[PortfolioDocument]] = collection.find.toFuture

  def add(item: PortfolioDocument): Future[Completed] = collection.insertOne(item).toFuture

  def findById(id: ObjectId): Future[Option[PortfolioDocument]] = {
    collection.find(byId(id)).first().toFutureOption
  }

  def update(id: ObjectId, item: PortfolioDocument): Future[UpdateResult] =
    collection.replaceOne(byId(id), item).toFuture
}
