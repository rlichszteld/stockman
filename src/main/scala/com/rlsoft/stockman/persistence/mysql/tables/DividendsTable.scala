package com.rlsoft.stockman.persistence.mysql.tables

import java.time.LocalDate

import com.rlsoft.stockman.persistence.mysql.AppMySqlProfile.api._
import com.rlsoft.stockman.persistence.mysql.abstraction.TableWithId
import com.rlsoft.stockman.persistence.mysql.entities.DividendEntity
import slick.lifted.ProvenShape

class DividendsTable(tag: Tag) extends Table[DividendEntity](tag, "dividends") with TableWithId[Int] {
  override def id: Rep[Int] = column[Int]("id")

  def date: Rep[LocalDate] = column[LocalDate]("date")
  def value: Rep[BigDecimal] = column[BigDecimal]("value")

  override def * : ProvenShape[DividendEntity] = (id, date, value) <> (DividendEntity.tupled, DividendEntity.unapply)
}
