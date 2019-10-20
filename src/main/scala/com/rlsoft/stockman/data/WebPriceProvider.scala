package com.rlsoft.stockman.data

import com.rlsoft.stockman.http.client.FinClient
import com.rlsoft.stockman.http.client.models.TicketPrice

import scala.concurrent.Future

class WebPriceProvider(finClient: FinClient) extends PriceProvider {
  override def getPrice(symbol: String): Future[TicketPrice] = {
    finClient.getTicketPrice(symbol)
  }
}
