package com.rlsoft.stockman.persistence.mysql.repositories

import com.rlsoft.stockman.persistence.mysql.AppMySqlProfile.api._
import com.rlsoft.stockman.persistence.mysql.abstraction.Repository
import com.rlsoft.stockman.persistence.mysql.entities.TransactionEntity
import com.rlsoft.stockman.persistence.mysql.tables.{Tables, TransactionsTable}
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext

class TransactionRepository(val database: Database)(
    implicit ec: ExecutionContext
) extends Repository[TransactionEntity, TransactionsTable, Int] {
  override val tableQuery = Tables.transactions
}
