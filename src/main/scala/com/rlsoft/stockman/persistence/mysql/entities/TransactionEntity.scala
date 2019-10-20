package com.rlsoft.stockman.persistence.mysql.entities

import java.time.LocalDate

case class TransactionEntity(id: Int,
                             holdingId: Int,
                             date: LocalDate,
                             amount: Int,
                             price: BigDecimal,
                             commission: BigDecimal,
                             isBuy: Boolean)
    extends BaseEntity
