package com.rlsoft.stockman.persistence.mysql.entities

import java.time.LocalDate

case class DividendEntity(id: Int, date: LocalDate, value: BigDecimal) extends BaseEntity
