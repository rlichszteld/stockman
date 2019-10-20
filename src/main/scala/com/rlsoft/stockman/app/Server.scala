package com.rlsoft.stockman.app

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import com.rlsoft.stockman.config.HttpConfig
import com.rlsoft.stockman.http.routers.Router
import com.typesafe.scalalogging.StrictLogging

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.io.StdIn
import scala.language.postfixOps

class Server(config: HttpConfig, router: Router)(implicit system: ActorSystem, mat: Materializer, ec: ExecutionContext)
    extends StrictLogging {

  var bindingFuture: Future[Http.ServerBinding] = _

  def start(): Unit = {
    logger.info("Starting up...")
    bindingFuture = Http().bindAndHandle(router.routes, "localhost", config.port)

    logger.info(s"Server online at http://localhost:${config.port}")

    StdIn.readLine("Press ENTER to terminate...")
  }

  def stop: Future[Http.HttpTerminated] = {
    logger.info("Shutting down...")
    Await.result(bindingFuture, 10 seconds).terminate(hardDeadline = 3 seconds)
    //Await.result(bindingFuture.flatMap(_.unbind), 10 seconds)
  }
}
