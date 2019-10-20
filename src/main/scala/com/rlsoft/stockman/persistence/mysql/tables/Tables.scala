package com.rlsoft.stockman.persistence.mysql.tables

import slick.lifted.TableQuery

object Tables {
  val portfolios = TableQuery[PortfoliosTable]
  val holdings = TableQuery[HoldingsTable]
  val dividends = TableQuery[DividendsTable]
  val transactions = TableQuery[TransactionsTable]
  val tickets = TableQuery[TicketsTable]
}
