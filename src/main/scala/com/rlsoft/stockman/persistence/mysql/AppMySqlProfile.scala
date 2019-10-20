package com.rlsoft.stockman.persistence.mysql

import java.sql.Timestamp
import java.time.{LocalDate, LocalDateTime}

import slick.jdbc.{JdbcType, MySQLProfile}

trait AppMySqlProfile extends MySQLProfile {

  trait AppSqlApi extends API {
    implicit val localDateConverter: JdbcType[LocalDate] =
      MappedColumnType.base[LocalDate, String](_.toString, LocalDate.parse)

    implicit val localDateTimeConverter: JdbcType[LocalDateTime] =
      MappedColumnType.base[LocalDateTime, Timestamp](Timestamp.valueOf, _.toLocalDateTime)
  }

  override val api: AppSqlApi = new AppSqlApi {}
}

object AppMySqlProfile extends AppMySqlProfile
