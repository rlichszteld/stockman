package com.rlsoft.stockman.utils

import java.time.LocalDate
import java.time.temporal.ChronoUnit

object TimeUtils {
  val AVG_DAYS_PER_YEAR = 365.2425

  def yearsFrac(startDate: LocalDate, endDate: LocalDate): Double =
    startDate.until(endDate, ChronoUnit.DAYS) / AVG_DAYS_PER_YEAR;
}
