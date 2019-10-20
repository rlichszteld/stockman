package com.rlsoft.stockman.persistence.mysql.tables

import java.time.LocalDate

import com.rlsoft.stockman.persistence.mysql.AppMySqlProfile.api._
import com.rlsoft.stockman.persistence.mysql.abstraction.TableWithId
import com.rlsoft.stockman.persistence.mysql.entities.TransactionEntity
import slick.lifted.ProvenShape

class TransactionsTable(tag: Tag) extends Table[TransactionEntity](tag, "transactions") with TableWithId[Int] {
  override def id: Rep[Int] = column[Int]("id")

  def holdingId: Rep[Int] = column[Int]("holding_id")
  def date: Rep[LocalDate] = column[LocalDate]("date")
  def amount: Rep[Int] = column[Int]("amount")
  def price: Rep[BigDecimal] = column[BigDecimal]("price")
  def commission: Rep[BigDecimal] = column[BigDecimal]("commission")
  def isBuy: Rep[Boolean] = column[Boolean]("is_buy")

  override def * : ProvenShape[TransactionEntity] =
    (id, holdingId, date, amount, price, commission, isBuy) <> (TransactionEntity.tupled, TransactionEntity.unapply)
}
