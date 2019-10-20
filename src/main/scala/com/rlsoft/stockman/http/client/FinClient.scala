package com.rlsoft.stockman.http.client

import com.rlsoft.stockman.http.client.models.TicketPrice
import com.rlsoft.stockman.models.Ticket

import scala.concurrent.Future

trait FinClient {
  def getTicketInfo(symbol: String): Future[Ticket]

  def getTicketPrice(symbol: String): Future[TicketPrice]
}
