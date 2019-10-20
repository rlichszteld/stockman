package com.rlsoft.stockman.config

import com.rlsoft.stockman.models.{Dividend, Holding, HoldingTransaction, Ticket}
import com.rlsoft.stockman.persistence.mongodb.PortfolioDocument
import com.rlsoft.stockman.persistence.mongodb.codecs.BigDecimalCodec
import org.bson.codecs.configuration.CodecRegistries.{fromCodecs, fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.{MongoClient, MongoDatabase}

case class MongoDBConfig(dbName: String) {
  val modelCodecs: CodecRegistry = fromProviders(classOf[PortfolioDocument],
                                                 classOf[Holding],
                                                 classOf[Ticket],
                                                 classOf[HoldingTransaction],
                                                 classOf[Dividend])

  val customCodecs: CodecRegistry = fromCodecs(new BigDecimalCodec)

  val codecRegistry: CodecRegistry = fromRegistries(modelCodecs, customCodecs, DEFAULT_CODEC_REGISTRY)

  val mongoClient = MongoClient()
  val db: MongoDatabase = mongoClient.getDatabase(dbName).withCodecRegistry(codecRegistry)
}
