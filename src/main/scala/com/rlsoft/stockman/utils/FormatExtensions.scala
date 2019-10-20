package com.rlsoft.stockman.utils

import java.text.DecimalFormat

object FormatExtensions {
  def format[T: Numeric](x: T, precision: Int = 2, prefix: String = "", suffix: String = ""): String = {
    val formatter = new DecimalFormat
    formatter.setMinimumFractionDigits(precision)
    formatter.setMaximumFractionDigits(precision)
    s"$prefix${formatter.format(x)}$suffix"
  }

  implicit class NumericOps[T: Numeric](value: T) {
    def asCurrency(currency: String = "£"): String = format(value, prefix = currency)

    def asPercent: String = format(value, suffix = "%")

    def asInt: String = format(value, precision = 0)
  }
}
