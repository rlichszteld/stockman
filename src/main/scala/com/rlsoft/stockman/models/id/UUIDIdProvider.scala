package com.rlsoft.stockman.models.id

import java.util.UUID

object UUIDIdProvider extends IdProvider[UUID] {
  override def generateId: UUID = UUID.randomUUID()
}
