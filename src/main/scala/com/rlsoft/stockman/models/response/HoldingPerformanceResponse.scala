package com.rlsoft.stockman.models.response

import java.time.LocalDate

import com.rlsoft.stockman.business.HoldingPerformance
import com.rlsoft.stockman.models.Ticket

case class HoldingPerformanceResponse(ticket: Ticket,
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

object HoldingPerformanceResponse {
  def apply(holdingPerformance: HoldingPerformance): HoldingPerformanceResponse =
    HoldingPerformanceResponse(
      holdingPerformance.holding.ticket,
      startDate = holdingPerformance.startDate,
      holdingSize = holdingPerformance.holdingSize,
      bookValue = holdingPerformance.bookValue,
      commission = holdingPerformance.commission,
      initialValue = holdingPerformance.initialValue,
      avgYears = holdingPerformance.avgYears,
      currentValue = holdingPerformance.currentValue,
      capitalGain = holdingPerformance.capitalGain,
      dividends = holdingPerformance.dividends,
      totalGain = holdingPerformance.totalGain,
      totalReturn = holdingPerformance.totalReturn,
      cagr = holdingPerformance.cagr
    )
}
