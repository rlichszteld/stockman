package com.rlsoft.stockman.http.directives

import akka.http.scaladsl.marshalling.ToEntityMarshaller
import akka.http.scaladsl.model.{StatusCode, StatusCodes}
import akka.http.scaladsl.server.Directives.onSuccess
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.RouteDirectives
import com.rlsoft.stockman.errors.Result
import com.rlsoft.stockman.http.errors.ErrorMapper

import scala.concurrent.Future

trait ResultDirectives extends RouteDirectives {
  def complete[T: ToEntityMarshaller](f: Future[Result[T]], statusCode: StatusCode = StatusCodes.OK)(
      implicit handler: ErrorMapper
  ): Route = {
    onSuccess(f) { value =>
      value.fold(
        error => handler(error),
        result => super.complete(statusCode -> result)
      )
    }
  }
}
