package com.rlsoft.stockman.utils

import org.scalatest.{Matchers, WordSpec}

class MathUtilsTest extends WordSpec with Matchers {

  "#weightedAvg" should {
    "calculated weighted average of input arguments" in {
      val weightedInput: Seq[(Double, Double)] = Seq((10, 2), (50, 5), (40, 3))

      val result = MathUtils.weightedAvg(weightedInput)

      result shouldBe 39
    }
  }

}
