package com.rlsoft.stockman.http.client.models

import java.time.LocalDate

case class TicketPrice(symbol: String, price: BigDecimal, date: LocalDate, change: Double)
