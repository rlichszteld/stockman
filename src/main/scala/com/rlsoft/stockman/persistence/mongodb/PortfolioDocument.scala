package com.rlsoft.stockman.persistence.mongodb

import com.rlsoft.stockman.models._
import org.mongodb.scala.bson.ObjectId

case class PortfolioDocument(_id: ObjectId, name: String, holdings: Seq[Holding] = Seq()) {
  def toPortfolio: Portfolio = Portfolio(name, holdings)
}

object PortfolioDocument {
  def apply(portfolio: Portfolio): PortfolioDocument =
    new PortfolioDocument(_id = new ObjectId(), name = portfolio.name, holdings = portfolio.holdings)
}
