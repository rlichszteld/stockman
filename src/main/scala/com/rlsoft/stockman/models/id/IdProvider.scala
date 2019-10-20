package com.rlsoft.stockman.models.id

trait IdProvider[T] {
  def generateId: T
}
