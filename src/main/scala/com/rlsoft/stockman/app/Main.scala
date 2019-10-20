package com.rlsoft.stockman.app

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.rlsoft.stockman.config.AppConfig
import com.rlsoft.stockman.http.routers.PortfolioRouter
import com.rlsoft.stockman.persistence.mongodb.PortfolioRepository
import com.rlsoft.stockman.service.PortfolioService

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("StockMan")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContext = system.dispatcher

  implicit val futureTimeout: FiniteDuration = 10 seconds

  val config = AppConfig.load()

  val repo = new PortfolioRepository(config.mongoDb.db)
  val service = new PortfolioService(repo)

  //repo.add(PortfolioDocument(StaticData.portfolio)).toValue

  val router = new PortfolioRouter(service)
  val server = new Server(config.http, router)

  server.start()

  server.stop.flatMap { _ =>
    config.mongoDb.mongoClient.close()
    system.terminate()
  }

}
