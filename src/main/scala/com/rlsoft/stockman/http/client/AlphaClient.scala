package com.rlsoft.stockman.http.client

import java.time.LocalDate

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, Uri}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.Materializer
import com.rlsoft.stockman.config.AlphaConfig
import com.rlsoft.stockman.http.client.models.TicketPrice
import com.rlsoft.stockman.models.Ticket
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.{Decoder, HCursor}

import scala.concurrent.{ExecutionContext, Future}

case class TicketInfoResponse(bestMatches: Seq[Ticket])

trait TicketJsonProtocol {

  implicit val ticketDecoder: Decoder[Ticket] = (c: HCursor) =>
    for {
      symbol <- c.get[String]("1. symbol")
      name <- c.get[String]("2. name")
    } yield Ticket(symbol, name)
}

trait TicketPriceJsonProtocol {
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

class AlphaClient(config: AlphaConfig)(implicit system: ActorSystem, mat: Materializer, ec: ExecutionContext)
    extends FinClient
    with FailFastCirceSupport
    with TicketJsonProtocol
    with TicketPriceJsonProtocol {

  private lazy val baseUrl = config.baseUrl
  private lazy val apiKey = config.apiKey

  override def getTicketInfo(symbol: String): Future[Ticket] = {
    val url = s"$baseUrl/query?function=SYMBOL_SEARCH&keywords=$symbol&apikey=$apiKey"

    val request = HttpRequest(method = HttpMethods.GET, uri = Uri(url))
    executeRequest[TicketInfoResponse](request).map(_.bestMatches.head)
  }

  override def getTicketPrice(symbol: String): Future[TicketPrice] = {
    val url = s"$baseUrl/query?function=GLOBAL_QUOTE&symbol=$symbol&apikey=$apiKey"

    val request = HttpRequest(method = HttpMethods.GET, uri = Uri(url))
    executeRequest[TicketPrice](request)
  }

  private def executeRequest[T: Decoder](request: HttpRequest): Future[T] = {
    Http().singleRequest(request).flatMap { response =>
      Unmarshal(response).to[T]
    }
  }
}
