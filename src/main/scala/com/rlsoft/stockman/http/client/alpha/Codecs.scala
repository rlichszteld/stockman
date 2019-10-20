package com.rlsoft.stockman.http.client.alpha

import java.time.LocalDate

import com.rlsoft.stockman.http.client.models.TicketPrice
import com.rlsoft.stockman.models.Ticket
import io.circe.{Decoder, HCursor}

trait TicketJsonSupport {

  implicit val ticketDecoder: Decoder[Ticket] = (c: HCursor) =>
    for {
      symbol <- c.get[String]("1. symbol")
      name <- c.get[String]("2. name")
    } yield Ticket(symbol, name)
}

trait TicketPriceJsonSupport {

  implicit val ticketPriceDecoder: Decoder[TicketPrice] = (root: HCursor) => {
    val c = root.downField("Global Quote")
    for {
      symbol <- c.get[String]("01. symbol")
      price <- c.get[BigDecimal]("05. price")
      date <- c.get[LocalDate]("07. latest trading day")
      change <- c.get[Double]("09. change")
    } yield TicketPrice(symbol, price, date, change)
  }
}
