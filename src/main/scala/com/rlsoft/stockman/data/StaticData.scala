package com.rlsoft.stockman.data

import java.time.LocalDate

import com.rlsoft.stockman.models._

object StaticData {
  val `888` = Ticket("888.L", "888 Holdings Public Limited Company")
  val `888Holding` = Holding(
    ticket = `888`,
    buys = Seq(
      HoldingTransaction(date = LocalDate.of(2019, 7, 15),
                         amount = 655,
                         price = BigDecimal(1.5569),
                         fees = BigDecimal(0.0))
    )
  )

  val asos = Ticket("ASC.L", "Asos Plc")
  val asosHolding = Holding(
    ticket = asos,
    buys = Seq(
      HoldingTransaction(date = LocalDate.of(2018, 12, 18),
                         amount = 74,
                         price = BigDecimal(26.8),
                         fees = BigDecimal(6.0)),
      HoldingTransaction(date = LocalDate.of(2018, 12, 20),
                         amount = 89,
                         price = BigDecimal(22.5764),
                         fees = BigDecimal(6.0))
    )
  )

  val evraz = Ticket("EVR.L", "Evraz Plc")
  val evrazHolding = Holding(
    ticket = evraz,
    buys = Seq(
      HoldingTransaction(date = LocalDate.of(2018, 12, 14),
                         amount = 426,
                         price = BigDecimal(4.6494),
                         fees = BigDecimal(15.9)),
      HoldingTransaction(date = LocalDate.of(2019, 4, 1),
                         amount = 20,
                         price = BigDecimal(6.2850),
                         fees = BigDecimal(0.63)),
      HoldingTransaction(date = LocalDate.of(2019, 9, 9),
                         amount = 25,
                         price = BigDecimal(4.9260),
                         fees = BigDecimal(0.61))
    ),
    dividends = Seq(
      Dividend(date = LocalDate.of(2019, 3, 28), value = BigDecimal(128.6)),
      Dividend(date = LocalDate.of(2019, 9, 5), value = BigDecimal(125.35))
    )
  )

  val jd = Ticket("JD.L", "Jd Sports Fashion Plc")
  val jdHolding = Holding(
    ticket = jd,
    buys = Seq(
      HoldingTransaction(date = LocalDate.of(2019, 7, 10),
                         amount = 331,
                         price = BigDecimal(6.0084),
                         fees = BigDecimal(9.94))
    )
  )

  val morrison = Ticket("MRW.L", "Wm Morrison Supermarkets Plc")
  val morrisonHolding = Holding(
    ticket = morrison,
    buys = Seq(
      HoldingTransaction(date = LocalDate.of(2019, 7, 12),
                         amount = 970,
                         price = BigDecimal(2.0498),
                         fees = BigDecimal(9.94))
    )
  )

  val psn = Ticket("PSN.L", "Persimmon Plc")
  val psnHolding = Holding(
    ticket = psn,
    buys = Seq(
      HoldingTransaction(date = LocalDate.of(2018, 12, 10),
                         amount = 104,
                         price = BigDecimal(18.994142),
                         fees = BigDecimal(15.88)),
      HoldingTransaction(date = LocalDate.of(2019, 4, 2),
                         amount = 6,
                         price = BigDecimal(21.39),
                         fees = BigDecimal(0.64)),
      HoldingTransaction(date = LocalDate.of(2019, 7, 4), amount = 6, price = BigDecimal(19.99), fees = BigDecimal(0.6))
    ),
    dividends = Seq(
      Dividend(date = LocalDate.of(2019, 3, 29), value = BigDecimal(130.0)),
      Dividend(date = LocalDate.of(2019, 7, 2), value = BigDecimal(121.0))
    )
  )

  val rdsb = Ticket("RDSB.L", "Royal Dutch Shell Plc Class B")
  val rdsbHolding = Holding(
    ticket = rdsb,
    buys = Seq(
      HoldingTransaction(date = LocalDate.of(2018, 12, 13),
                         amount = 84,
                         price = BigDecimal(23.4980),
                         fees = BigDecimal(15.87)),
      HoldingTransaction(date = LocalDate.of(2019, 3, 27),
                         amount = 1,
                         price = BigDecimal(23.84),
                         fees = BigDecimal(0.12)),
      HoldingTransaction(date = LocalDate.of(2019, 6, 26),
                         amount = 1,
                         price = BigDecimal(26.24),
                         fees = BigDecimal(0.13)),
      HoldingTransaction(date = LocalDate.of(2019, 9, 25),
                         amount = 1,
                         price = BigDecimal(23.19),
                         fees = BigDecimal(0.12))
    ),
    dividends = Seq(
      Dividend(date = LocalDate.of(2019, 3, 25), value = BigDecimal(30.18)),
      Dividend(date = LocalDate.of(2019, 6, 24), value = BigDecimal(31.42)),
      Dividend(date = LocalDate.of(2019, 9, 23), value = BigDecimal(32.68))
    )
  )

  val royalMail = Ticket("RMG.L", "Royal Mail Plc")
  val royalMailHolding = Holding(
    ticket = royalMail,
    buys = Seq(
      HoldingTransaction(date = LocalDate.of(2019, 3, 5),
                         amount = 723,
                         price = BigDecimal(2.7494),
                         fees = BigDecimal(9.94)),
      HoldingTransaction(date = LocalDate.of(2019, 9, 6),
                         amount = 55,
                         price = BigDecimal(2.2110),
                         fees = BigDecimal(0.62))
    ),
    dividends = Seq(
      Dividend(date = LocalDate.of(2019, 9, 4), value = BigDecimal(122.91))
    )
  )

  val csp1 = Ticket("CSP1.L", "iShares Core S&P 500 UCITS ETF")
  val csp1Holding = Holding(
    ticket = csp1,
    buys = Seq(HoldingTransaction(date = LocalDate.of(2019, 8, 2), amount = 8, price = BigDecimal(240.0966)))
  )

  val portfolio = Portfolio("UK",
                            holdings = Seq(`888Holding`,
                                           asosHolding,
                                           evrazHolding,
                                           jdHolding,
                                           morrisonHolding,
                                           psnHolding,
                                           rdsbHolding,
                                           royalMailHolding,
                                           csp1Holding))

  val priceProvider = new StaticPriceProvider(
    Map(
      `888`.symbol -> BigDecimal(1.62),
      asos.symbol -> BigDecimal(25.75),
      evraz.symbol -> BigDecimal(4.345),
      jd.symbol -> BigDecimal(7.608),
      morrison.symbol -> BigDecimal(2.033),
      psn.symbol -> BigDecimal(22.87),
      rdsb.symbol -> BigDecimal(22.82),
      royalMail.symbol -> BigDecimal(2.181),
      csp1.symbol -> BigDecimal(237.17)
    )
  )
}
