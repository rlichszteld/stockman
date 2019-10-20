package com.rlsoft.stockman.http

import akka.http.scaladsl.server.Route

package object routers {

  trait Router {
    val routes: Route
  }

}
