package com.rlsoft.stockman.persistence.mysql.repositories

import com.rlsoft.stockman.persistence.mysql.AppMySqlProfile.api._
import com.rlsoft.stockman.persistence.mysql.abstraction.Repository
import com.rlsoft.stockman.persistence.mysql.entities.TicketEntity
import com.rlsoft.stockman.persistence.mysql.tables.{Tables, TicketsTable}
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext

class TicketRepository(val database: Database)(
    implicit ec: ExecutionContext
) extends Repository[TicketEntity, TicketsTable, Int] {
  override val tableQuery = Tables.tickets
}
