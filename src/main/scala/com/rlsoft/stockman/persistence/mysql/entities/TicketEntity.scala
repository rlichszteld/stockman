package com.rlsoft.stockman.persistence.mysql.entities

import com.rlsoft.stockman.persistence.mysql.abstraction.EntityWithId

case class TicketEntity(id: Int, symbol: String, name: String) extends EntityWithId[Int]
