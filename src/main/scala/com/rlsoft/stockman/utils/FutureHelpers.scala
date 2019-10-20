package com.rlsoft.stockman.utils

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.language.postfixOps

object FutureHelpers {
  implicit class FutureOps[T](f: Future[T]) {
    def toValue(implicit timeout: Duration): T = Await.result(f, timeout)
  }
}
