package com.rlsoft.stockman.persistence.mysql.tables

import com.rlsoft.stockman.persistence.mysql.abstraction.TableWithId
import com.rlsoft.stockman.persistence.mysql.entities.TicketEntity
import slick.lifted.{ProvenShape, Rep}
import com.rlsoft.stockman.persistence.mysql.AppMySqlProfile.api._

class TicketsTable(tag: Tag) extends Table[TicketEntity](tag, "tickets") with TableWithId[Int] {
  override def id: Rep[Int] = column[Int]("id")

  def symbol: Rep[String] = column[String]("symbol")
  def name: Rep[String] = column[String]("name")

  override def * : ProvenShape[TicketEntity] = (id, symbol, name) <> (TicketEntity.tupled, TicketEntity.unapply)
}
