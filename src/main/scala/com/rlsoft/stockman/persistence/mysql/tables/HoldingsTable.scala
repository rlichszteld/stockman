package com.rlsoft.stockman.persistence.mysql.tables

import com.rlsoft.stockman.persistence.mysql.AppMySqlProfile.api._
import com.rlsoft.stockman.persistence.mysql.abstraction.TableWithId
import com.rlsoft.stockman.persistence.mysql.entities.HoldingEntity
import slick.lifted.ProvenShape

class HoldingsTable(tag: Tag) extends Table[HoldingEntity](tag, "holdings") with TableWithId[Int] {
  override def id: Rep[Int] = column[Int]("id")

  def ticketId: Rep[Int] = column[Int]("ticket_id")

  override def * : ProvenShape[HoldingEntity] = (id, ticketId) <> (HoldingEntity.tupled, HoldingEntity.unapply)
}
