package com.rlsoft.stockman.business

import java.time.LocalDate

import com.rlsoft.stockman.data.PriceProvider
import com.rlsoft.stockman.models.{Holding, Portfolio, TransactionCosts}
import com.rlsoft.stockman.utils.{MathUtils, TimeUtils}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

case class HoldingPerformance(holding: Holding,
                              startDate: LocalDate,
                              holdingSize: Int,
                              bookValue: BigDecimal,
                              commission: BigDecimal,
                              initialValue: BigDecimal,
                              avgYears: Double,
                              currentValue: BigDecimal,
                              capitalGain: BigDecimal,
                              dividends: BigDecimal,
                              totalGain: BigDecimal,
                              totalReturn: Double,
                              cagr: Double)

case class PortfolioPerformance(portfolio: Portfolio,
                                startDate: LocalDate,
                                portfolioSize: Int,
                                initialValue: BigDecimal,
                                avgYears: Double,
                                currentValue: BigDecimal,
                                capitalGain: BigDecimal,
                                dividends: BigDecimal,
                                totalGain: BigDecimal,
                                totalReturn: Double,
                                cagr: Double,
                                holdingsPerformance: Seq[HoldingPerformance])

object HoldingAnalyser {
  def analyseHolding(holding: Holding,
                     currentPrice: BigDecimal,
                     endDate: LocalDate = LocalDate.now()): HoldingPerformance = {
    val startDate = holding.buys.sortWith(_.date.toEpochDay < _.date.toEpochDay).head.date

    val holdingSize = holding.buys.map(_.amount).sum - holding.sells.map(_.amount).sum

    val costs = holding.buys.foldLeft(TransactionCosts(0, 0))(_ + _.costs)
    val bookValue = costs.bookValue
    val commission = costs.commission
    val initialValue = costs.totalValue

    val weightedTimes =
      holding.buys.map(x => (TimeUtils.yearsFrac(x.date, endDate), (x.costs.totalValue / initialValue).toDouble))
    val avgYears = MathUtils.weightedAvg(weightedTimes)

    val currentValue = holdingSize * currentPrice
    val capitalGain = currentValue - initialValue
    val dividends = holding.dividends.map(_.value).sum
    val totalGain = capitalGain + dividends
    val totalReturn = (totalGain / initialValue).toDouble
    val cagr = Math.pow(1 + totalReturn, 1 / avgYears) - 1

    HoldingPerformance(holding,
                       startDate,
                       holdingSize,
                       bookValue,
                       commission,
                       initialValue,
                       avgYears,
                       currentValue,
                       capitalGain,
                       dividends,
                       totalGain,
                       totalReturn,
                       cagr)
  }

  def analyzePortfolio(portfolio: Portfolio,
                       priceProvider: PriceProvider,
                       endDate: LocalDate = LocalDate.now()): PortfolioPerformance = {
    val holdingsPerf = portfolio.holdings.map { holding =>
      val currentPrice = Await.result(priceProvider.getPrice(holding.ticket.symbol), 1 second).price
      analyseHolding(holding, currentPrice, endDate)
    }

    val startDate = holdingsPerf.sortWith(_.startDate.toEpochDay < _.startDate.toEpochDay).head.startDate
    val portfolioSize = holdingsPerf.map(_.holdingSize).sum

    val initialValue = holdingsPerf.map(_.initialValue).sum
    val currentValue = holdingsPerf.map(_.currentValue).sum
    val dividends = holdingsPerf.map(_.dividends).sum

    val weightedTimes = holdingsPerf.map(x => (x.avgYears, (x.initialValue / initialValue).toDouble))
    val avgYears = MathUtils.weightedAvg(weightedTimes)

    val capitalGain = currentValue - initialValue
    val totalGain = capitalGain + dividends
    val totalReturn = (totalGain / initialValue).toDouble
    val cagr = Math.pow(1 + totalReturn, 1 / avgYears) - 1

    PortfolioPerformance(portfolio,
                         startDate,
                         portfolioSize,
                         initialValue,
                         avgYears,
                         currentValue,
                         capitalGain,
                         dividends,
                         totalGain,
                         totalReturn,
                         cagr,
                         holdingsPerf)
  }
}
