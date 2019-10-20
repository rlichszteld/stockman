package com.rlsoft.stockman.http.routers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{PathMatcher1, Route}
import com.rlsoft.stockman.data.StaticData
import com.rlsoft.stockman.http.directives.ResultDirectives
import com.rlsoft.stockman.http.errors._
import com.rlsoft.stockman.models.id.ObjectIdSupport
import com.rlsoft.stockman.service.PortfolioService
import com.typesafe.scalalogging.StrictLogging
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import org.bson.types.ObjectId

import scala.concurrent.ExecutionContext

class PortfolioRouter(service: PortfolioService)(implicit ec: ExecutionContext)
    extends Router
    with FailFastCirceSupport
    with StrictLogging
    with ResultDirectives
    with ErrorHandler
    with ObjectIdSupport {

  val ObjectID: PathMatcher1[ObjectId] = Segment.map(new ObjectId(_))

  private val list = pathEndOrSingleSlash {
    get {
      complete(StatusCodes.OK -> service.getAll)
    }
  }

  private val getOne = path(ObjectID) { objectId =>
    get {
      complete(service.getPortfolio(objectId))
    }
  }

  private val analyze = path(ObjectID / "analyze") { objectId =>
    get {
      complete(service.analyzePortfolio(objectId))
    }
  }

  private val test = path("test") {
    get {
      complete(StaticData.portfolio)
    }
  }

  val routes: Route = {
    pathPrefix("portfolios") {
      list ~ getOne ~ analyze
    }
  } ~ test
}
