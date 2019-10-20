package com.rlsoft.stockman.data
import java.time.LocalDate

import com.rlsoft.stockman.http.client.models.TicketPrice

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

class StaticPriceProvider(prices: Map[String, BigDecimal]) extends PriceProvider {
  override def getPrice(symbol: String): Future[TicketPrice] = {
    Try(prices(symbol)) match {
      case Success(value) => Future.successful(TicketPrice(symbol, value, LocalDate.now(), 0.0))
      case Failure(e) => Future.failed(e)
    }
  }
}
