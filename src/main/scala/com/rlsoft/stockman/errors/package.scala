package com.rlsoft.stockman

package object errors {
  type Result[T] = Either[AppError, T]
}
