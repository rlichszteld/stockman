package com.rlsoft.stockman.persistence.mysql.abstraction

import slick.lifted.Rep

trait TableWithId[PK] {
  def id: Rep[PK]
}
