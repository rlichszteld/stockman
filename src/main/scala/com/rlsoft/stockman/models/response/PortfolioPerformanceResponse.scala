package com.rlsoft.stockman.models.response

import java.time.LocalDate

import com.rlsoft.stockman.business.PortfolioPerformance

case class PortfolioPerformanceResponse(portfolio: String,
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
                                        holdings: Seq[HoldingPerformanceResponse])

object PortfolioPerformanceResponse {
  def apply(portfolioPerformance: PortfolioPerformance): PortfolioPerformanceResponse =
    PortfolioPerformanceResponse(
      portfolio = portfolioPerformance.portfolio.name,
      startDate = portfolioPerformance.startDate,
      portfolioSize = portfolioPerformance.portfolioSize,
      initialValue = portfolioPerformance.initialValue,
      avgYears = portfolioPerformance.avgYears,
      currentValue = portfolioPerformance.currentValue,
      capitalGain = portfolioPerformance.capitalGain,
      dividends = portfolioPerformance.dividends,
      totalGain = portfolioPerformance.totalGain,
      totalReturn = portfolioPerformance.totalReturn,
      cagr = portfolioPerformance.cagr,
      holdings = portfolioPerformance.holdingsPerformance.map(HoldingPerformanceResponse.apply)
    )
}
