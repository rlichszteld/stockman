package com.rlsoft.stockman.persistence.mysql.abstraction

trait EntityWithId[PK] {
  val id: PK
}
