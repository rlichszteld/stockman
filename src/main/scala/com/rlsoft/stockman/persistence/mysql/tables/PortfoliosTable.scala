package com.rlsoft.stockman.persistence.mysql.tables

import com.rlsoft.stockman.persistence.mysql.AppMySqlProfile.api._
import com.rlsoft.stockman.persistence.mysql.abstraction.TableWithId
import com.rlsoft.stockman.persistence.mysql.entities.PortfolioEntity
import slick.lifted.{ProvenShape, Rep}

class PortfoliosTable(tag: Tag) extends Table[PortfolioEntity](tag, "portfolios") with TableWithId[Int] {
  override def id: Rep[Int] = column[Int]("id")

  def name: Rep[String] = column[String]("name")

  override def * : ProvenShape[PortfolioEntity] = (id, name) <> (PortfolioEntity.tupled, PortfolioEntity.unapply)
}
