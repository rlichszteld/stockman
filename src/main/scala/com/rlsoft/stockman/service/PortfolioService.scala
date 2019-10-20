package com.rlsoft.stockman.service

import java.time.LocalDate

import cats.syntax.either._
import com.rlsoft.stockman.business.HoldingAnalyser
import com.rlsoft.stockman.data.StaticData
import com.rlsoft.stockman.errors.{ResourceNotFound, Result}
import com.rlsoft.stockman.models.id.Id
import com.rlsoft.stockman.models.response.PortfolioPerformanceResponse
import com.rlsoft.stockman.persistence.mongodb.{PortfolioDocument, PortfolioRepository}

import scala.concurrent.{ExecutionContext, Future}

class PortfolioService(repo: PortfolioRepository)(implicit ec: ExecutionContext) {

  def getAll: Future[Seq[PortfolioDocument]] = repo.getAll

  def getPortfolio(portfolioId: Id): Future[Result[PortfolioDocument]] = {
    repo.findById(portfolioId).map(Either.fromOption(_, ResourceNotFound))
  }

  def analyzePortfolio(portfolioId: Id): Future[Result[PortfolioPerformanceResponse]] = {
    for {
      document <- getPortfolio(portfolioId)
      result = document.map(
        portfolio => HoldingAnalyser.analyzePortfolio(portfolio.toPortfolio, StaticData.priceProvider, LocalDate.now)
      )
    } yield result.map(PortfolioPerformanceResponse.apply)
  }
}
