package com.rlsoft.stockman.config

import slick.jdbc.JdbcBackend.Database

case class DBConfig(dbName: String, dbHost: String, url: String, username: String, password: String) {
  lazy val formattedUrl: String =
    s"$url/$dbName?autoReconnect=true&useSSL=false&createDatabaseIfNotExist=true&characterEncoding=UTF-8"

  lazy val database: Database = Database.forURL(
    url = formattedUrl,
    user = username,
    password = password
  )
}
