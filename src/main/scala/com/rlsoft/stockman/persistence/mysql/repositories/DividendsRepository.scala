package com.rlsoft.stockman.persistence.mysql.repositories

import com.rlsoft.stockman.persistence.mysql.AppMySqlProfile.api._
import com.rlsoft.stockman.persistence.mysql.abstraction.Repository
import com.rlsoft.stockman.persistence.mysql.entities.DividendEntity
import com.rlsoft.stockman.persistence.mysql.tables.{DividendsTable, Tables}
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext

class DividendsRepository(val database: Database)(
    implicit ec: ExecutionContext
) extends Repository[DividendEntity, DividendsTable, Int] {
  override val tableQuery = Tables.dividends
}
