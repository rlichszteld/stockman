package com.rlsoft.stockman.http.errors

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.complete
import com.rlsoft.stockman.errors.ResourceNotFound

trait ErrorHandler {
  implicit val errorMapper: ErrorMapper = {
    case ResourceNotFound => complete(StatusCodes.NotFound)

    case _ => complete(StatusCodes.BadRequest)
  }
}
