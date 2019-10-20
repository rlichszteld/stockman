package com.rlsoft.stockman.app

import java.time.LocalDate

import com.rlsoft.stockman.business.{HoldingAnalyser, HoldingPerformance}
import com.rlsoft.stockman.data.StaticData
import com.rlsoft.stockman.utils.FormatExtensions._

object Console //extends App
{
  def printHoldingData(hData: HoldingPerformance, indentSize: Int = 2): Unit = {

    def printIndent(str: String, indentSize: Int = 2): Unit = {
      val indent = " " * indentSize
      println(s"$indent$str")
    }

    def printChild: String => Unit = printIndent(_, indentSize + 2)

    printIndent(s"Performance data for: ${hData.holding.ticket})")
    printChild(s"- Holding size: ${hData.holdingSize}")
    printChild(s"- Book cost: ${hData.bookValue.asCurrency()}")
    printChild(s"- Commission: ${hData.commission.asCurrency()}")
    printChild(s"- Initial value: ${(hData.bookValue + hData.commission).asCurrency()}")
    printChild(s"- Average years: ${format(hData.avgYears)}")

    printChild(s"- Current value: ${hData.currentValue.asCurrency()}")
    printChild(s"- Capital gain: ${hData.capitalGain.asCurrency()}")
    printChild(s"- Dividends: ${hData.dividends.asCurrency()}")
    printChild(s"- Total gain: ${hData.totalGain.asCurrency()}")
    printChild(s"- Total return: ${(100 * hData.totalReturn).asPercent}")
    printChild(s"- CAGR: ${(100 * hData.cagr).asPercent}")
    println()
  }

  val now = LocalDate.now()

  val pData = HoldingAnalyser.analyzePortfolio(StaticData.portfolio, StaticData.priceProvider, now)
  println(s"Performance data for portfolio '${pData.portfolio.name}'")
  println(s"- Portfolio size: ${pData.portfolioSize.asInt}")
  println(s"- Initial value: ${(pData.initialValue).asCurrency()}")
  println(s"- Average years: ${format(pData.avgYears)}")

  println(s"- Current value: ${pData.currentValue.asCurrency()}")
  println(s"- Capital gain: ${pData.capitalGain.asCurrency()}")
  println(s"- Dividends: ${pData.dividends.asCurrency()}")
  println(s"- Total gain: ${pData.totalGain.asCurrency()}")
  println(s"- Total return: ${(100 * pData.totalReturn).asPercent}")
  println(s"- CAGR: ${(100 * pData.cagr).asPercent}")

  println()
  println("Itemized details:")
  pData.holdingsPerformance.foreach(printHoldingData(_))
}
