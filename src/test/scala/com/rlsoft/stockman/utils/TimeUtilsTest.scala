package com.rlsoft.stockman.utils

import java.time.LocalDate

import org.scalatest.{Matchers, WordSpec}
import TimeUtils.AVG_DAYS_PER_YEAR

class TimeUtilsTest extends WordSpec with Matchers {

  "#yearsFrac" should {
    "calculate period between two dates as a fraction of a year" in {
      val startDate = LocalDate.of(2018, 2, 5)
      val endDate = LocalDate.of(2018, 2, 6)

      val result = TimeUtils.yearsFrac(startDate, endDate)
      result shouldBe 1 / AVG_DAYS_PER_YEAR
    }
  }

}
