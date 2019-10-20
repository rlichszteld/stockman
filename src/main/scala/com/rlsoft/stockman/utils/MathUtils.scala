package com.rlsoft.stockman.utils

object MathUtils {
  def weightedAvg[T: Fractional](weightedValues: Seq[(T, T)]): T = {
    weightedValues.foldLeft((implicitly[Fractional[T]].zero, implicitly[Fractional[T]].zero)) {
      case ((weightedSum, weights), (value, weight)) =>
        (
          implicitly[Numeric[T]].plus(weightedSum, implicitly[Numeric[T]].times(value, weight)),
          implicitly[Numeric[T]].plus(weights, weight)
        )
    } match {
      case (weightedSum, weights) => implicitly[Fractional[T]].div(weightedSum, weights)
    }
  }
}
