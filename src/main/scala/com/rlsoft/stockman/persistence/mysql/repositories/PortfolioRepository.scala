package com.rlsoft.stockman.persistence.mysql.repositories

import com.rlsoft.stockman.persistence.mysql.AppMySqlProfile.api._
import com.rlsoft.stockman.persistence.mysql.abstraction.Repository
import com.rlsoft.stockman.persistence.mysql.entities.PortfolioEntity
import com.rlsoft.stockman.persistence.mysql.tables.{PortfoliosTable, Tables}
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext

class PortfolioRepository(val database: Database)(
    implicit ec: ExecutionContext
) extends Repository[PortfolioEntity, PortfoliosTable, Int] {
  override val tableQuery = Tables.portfolios
}
