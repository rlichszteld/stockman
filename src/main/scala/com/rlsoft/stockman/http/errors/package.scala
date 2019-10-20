package com.rlsoft.stockman.http

import akka.http.scaladsl.server.StandardRoute
import com.rlsoft.stockman.errors.AppError

package object errors {
  type ErrorMapper = PartialFunction[AppError, StandardRoute]
}
