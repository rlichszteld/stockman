package com.rlsoft.stockman.http.client

import akka.stream.Materializer
import akka.stream.scaladsl.{Sink, Source}
import com.rlsoft.stockman.http.client.models.TicketPrice

import scala.collection.mutable.HashMap
import scala.concurrent.{ExecutionContext, Future}

class PriceUpdater(finClient: FinClient)(implicit ec: ExecutionContext, mat: Materializer) {

  def fetchUpdates(symbols: Seq[String]): Future[Map[String, BigDecimal]] =
    Source
      .fromIterator(() => symbols.map(_.toUpperCase).distinct.iterator)
      .mapAsync(parallelism = 4) { symbol =>
        finClient.getTicketPrice(symbol)
      }
      .runWith(Sink.seq[TicketPrice])
      .map { prices =>
        prices.map(ticketPrice => ticketPrice.symbol -> ticketPrice.price).toMap
      }
}
