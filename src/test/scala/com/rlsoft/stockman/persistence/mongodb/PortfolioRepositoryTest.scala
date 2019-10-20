package com.rlsoft.stockman.persistence.mongodb

import com.mongodb.BasicDBObject
import com.rlsoft.stockman.config.AppConfig
import com.rlsoft.stockman.testutils.FastPatience
import org.bson.types.ObjectId
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}

class PortfolioRepositoryTest extends WordSpec with Matchers with BeforeAndAfterEach with FastPatience {
  private val config = AppConfig.load()
  private val collection = config.mongoDb.db.getCollection[PortfolioDocument]("portfolios")
  private val sut = new PortfolioRepository(config.mongoDb.db)

  override def beforeEach(): Unit = {
    collection.deleteMany(new BasicDBObject()).toFuture.futureValue
  }

  override def afterEach() {}

  "PortfolioRepositoryTest" should {

    "update" in {
      val objectId = new ObjectId()
      val originalDoc = PortfolioDocument(objectId, "Test-update_before")

      collection.insertOne(originalDoc).toFuture.futureValue

      val modifiedDoc = originalDoc.copy(name = "Test-update_after")

      sut.update(objectId, modifiedDoc).futureValue

      val result = sut.findById(objectId).futureValue

      result shouldBe Some(modifiedDoc)
    }

    "getAll" in {
      val docs = (1 to 3) map { index =>
        PortfolioDocument(new ObjectId(), s"Test-getAll_0$index")
      }

      collection.insertMany(docs).toFuture.futureValue

      val result = sut.getAll.futureValue

      result should contain theSameElementsAs docs
    }

    "add" in {
      val objectId = new ObjectId()
      val doc = PortfolioDocument(objectId, "Test-add")

      sut.add(doc).futureValue

      val result = collection.find(new BasicDBObject("_id", objectId)).head.futureValue

      result shouldBe doc
    }

    "findById" in {
      val objectId = new ObjectId()
      val doc = PortfolioDocument(objectId, "Test-findById")
      collection.insertOne(doc).toFuture.futureValue

      val result = sut.findById(objectId).futureValue

      result shouldBe Some(doc)
    }

  }
}
