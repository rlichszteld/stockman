package com.rlsoft.stockman.data

import com.rlsoft.stockman.http.client.models.TicketPrice

import scala.concurrent.Future

trait PriceProvider {
  type PriceMap = Map[String, TicketPrice]

  def getPrice(symbol: String): Future[TicketPrice]
}
