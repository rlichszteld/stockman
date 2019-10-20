package com.rlsoft.stockman.http.client.alpha

import java.time.LocalDate

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, Uri}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.Materializer
import com.rlsoft.stockman.config.AlphaConfig
import com.rlsoft.stockman.http.client.FinClient
import com.rlsoft.stockman.http.client.models.TicketPrice
import com.rlsoft.stockman.models.Ticket
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.{Decoder, HCursor}

import scala.concurrent.{ExecutionContext, Future}

case class TicketInfoResponse(bestMatches: Seq[Ticket])

class AlphaClient(config: AlphaConfig)(implicit system: ActorSystem, mat: Materializer, ec: ExecutionContext)
    extends FinClient
    with FailFastCirceSupport
    with TicketJsonSupport
    with TicketPriceJsonSupport {

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
