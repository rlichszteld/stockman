package com.rlsoft.stockman.http

import java.time.LocalDate

import com.rlsoft.stockman.http.client.TicketPriceJsonProtocol
import com.rlsoft.stockman.http.client.models.TicketPrice
import io.circe.Json
import io.circe.parser._
import org.scalatest.{Matchers, WordSpec}

class TicketPriceJsonProtocolTest extends WordSpec with TicketPriceJsonProtocol with Matchers {

  "ticketPriceDecoder" should {

    "unmarshall ticket price info" in {
      val json =
        """
        {
          "Global Quote": {
            "01. symbol": "PSN.L",
            "02. open": "2023.0000",
            "03. high": "2042.0000",
            "04. low": "2008.0000",
            "05. price": "2014.0000",
            "06. volume": "385078",
            "07. latest trading day": "2019-10-09",
            "08. previous close": "2026.0000",
            "09. change": "-12.0000",
            "10. change percent": "-0.5923%"
          }
        }""".stripMargin

      val expected = TicketPrice(
        symbol = "PSN.L",
        price = BigDecimal(2014.0000),
        date = LocalDate.of(2019, 10, 9),
        change = -12.0
      )
      val result = parse(json).getOrElse(Json.Null).as[TicketPrice]

      result.fold(e => fail(e.message), r => r shouldBe expected)
    }

  }
}
