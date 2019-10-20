package com.rlsoft.stockman.http.client.alpha

import java.time.LocalDate

import com.rlsoft.stockman.http.client.models.TicketPrice
import com.rlsoft.stockman.models.Ticket
import io.circe.Json
import io.circe.parser.parse
import org.scalatest.{Matchers, WordSpec}

class CodecsTest extends WordSpec with TicketJsonSupport with TicketPriceJsonSupport with Matchers {
  "ticketDecoder" should {

    "unmarshall ticket info" in {
      val json =
        """
          {
            "1. symbol": "PSN.LON",
            "2. name": "Persimmon Plc",
            "3. type": "Equity",
            "4. region": "United Kingdom",
            "5. marketOpen": "08:00",
            "6. marketClose": "16:30",
            "7. timezone": "UTC+01",
            "8. currency": "GBP",
            "9. matchScore": "1.0000"
          }
          """.stripMargin

      val expected = Ticket("PSN.LON", "Persimmon Plc")
      val result = parse(json).getOrElse(Json.Null).as[Ticket]

      result.fold(e => fail(e.message), r => r shouldBe expected)
    }

  }

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
