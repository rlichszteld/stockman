package com.rlsoft.stockman.http

import com.rlsoft.stockman.http.client.TicketJsonProtocol
import com.rlsoft.stockman.models.Ticket
import io.circe.Json
import io.circe.parser._
import org.scalatest.{Matchers, WordSpec}

class TicketJsonProtocolTest extends WordSpec with TicketJsonProtocol with Matchers {

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
}
