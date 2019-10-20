package com.rlsoft.stockman.models

import java.time.LocalDate

import org.bson.types.ObjectId

case class Ticket(symbol: String, name: String) {
  override def toString: String = s"$name ($symbol)"
}

case class TransactionCosts(bookValue: BigDecimal, commission: BigDecimal) {
  val totalValue: BigDecimal = bookValue + commission

  def +(right: TransactionCosts): TransactionCosts =
    copy(this.bookValue + right.bookValue, this.commission + right.commission)
}

case class HoldingTransaction(date: LocalDate, amount: Int, price: BigDecimal, fees: BigDecimal = 0.0) {
  def costs: TransactionCosts = TransactionCosts(amount * price, fees)
}

case class Dividend(date: LocalDate, value: BigDecimal)

case class Holding(ticket: Ticket,
                   buys: Seq[HoldingTransaction],
                   sells: Seq[HoldingTransaction] = Seq(),
                   dividends: Seq[Dividend] = Seq())

case class Portfolio(name: String, holdings: Seq[Holding])
