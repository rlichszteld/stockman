package com.rlsoft.stockman.testutils

import org.scalatest.concurrent.{PatienceConfiguration, ScalaFutures}
import org.scalatest.time.{Millis, Seconds, Span}

trait FastPatience extends PatienceConfiguration with ScalaFutures {

  override implicit def patienceConfig: PatienceConfig = PatienceConfig(
    timeout = scaled(Span(15, Seconds)),
    interval = scaled(Span(5, Millis))
  )
}
