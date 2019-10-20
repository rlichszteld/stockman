package com.rlsoft.stockman.persistence.mysql.repositories

import com.rlsoft.stockman.persistence.mysql.AppMySqlProfile.api._
import com.rlsoft.stockman.persistence.mysql.abstraction.Repository
import com.rlsoft.stockman.persistence.mysql.entities.HoldingEntity
import com.rlsoft.stockman.persistence.mysql.tables.{HoldingsTable, Tables}
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext

class HoldingRepository(val database: Database)(
    implicit ec: ExecutionContext
) extends Repository[HoldingEntity, HoldingsTable, Int] {
  override val tableQuery = Tables.holdings
}
