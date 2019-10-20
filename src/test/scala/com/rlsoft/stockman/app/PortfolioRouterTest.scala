package com.rlsoft.stockman.app

import java.time.LocalDate

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.rlsoft.stockman.http.errors.ErrorHandler
import com.rlsoft.stockman.http.routers.PortfolioRouter
import com.rlsoft.stockman.models.id.ObjectIdSupport
import com.rlsoft.stockman.models.response.PortfolioPerformanceResponse
import com.rlsoft.stockman.persistence.mongodb.PortfolioDocument
import com.rlsoft.stockman.service.PortfolioService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import org.bson.types.ObjectId
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.Future

class PortfolioRouterTest
    extends WordSpec
    with ScalatestRouteTest
    with Matchers
    with MockFactory
    with ErrorHandler
    with FailFastCirceSupport
    with ObjectIdSupport {

  private val portfolioServiceMock = mock[PortfolioService]
  private val router = new PortfolioRouter(portfolioServiceMock)

  "GET /portfolios" should {
    "return all documents" in {

      (portfolioServiceMock.getAll _)
        .expects()
        .returns(Future.successful(Seq()))

      Get("/portfolios") ~> Route.seal(router.routes) ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }

  "GET /portfolios/:id" should {
    "return requested document" in {
      val objectId = new ObjectId()
      val mockDocument = PortfolioDocument(objectId, "Test", Seq())

      (portfolioServiceMock.getPortfolio _)
        .expects(objectId)
        .returns(Future.successful(Right(mockDocument)))

      Get(s"/portfolios/$objectId") ~> Route.seal(router.routes) ~> check {
        status shouldBe StatusCodes.OK
        responseAs[PortfolioDocument] shouldBe mockDocument
      }
    }
  }

  "GET /portfolios/:id/analyze" should {
    "return analysis of requested portfolio" in {
      val objectId = new ObjectId()
      val mockDocument = PortfolioPerformanceResponse(
        portfolio = "",
        startDate = LocalDate.now,
        portfolioSize = 0,
        initialValue = 0.0,
        avgYears = 0.0,
        currentValue = 0.0,
        capitalGain = 0.0,
        dividends = 0.0,
        totalGain = 0.0,
        totalReturn = 0.0,
        cagr = 0.0,
        holdings = Seq()
      )

      (portfolioServiceMock.analyzePortfolio _)
        .expects(objectId)
        .returns(Future.successful(Right(mockDocument)))

      Get(s"/portfolios/$objectId/analyze") ~> Route.seal(router.routes) ~> check {
        status shouldBe StatusCodes.OK
        responseAs[PortfolioPerformanceResponse] shouldBe mockDocument
      }
    }
  }
}
