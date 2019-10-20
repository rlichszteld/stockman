package com.rlsoft.stockman.config

import pureconfig._
import pureconfig.generic.ProductHint
import pureconfig.generic.auto._

case class AppConfig(http: HttpConfig, alpha: AlphaConfig, mongoDb: MongoDBConfig)

object AppConfig {
  //implicit def hint[T]: ProductHint[T] = ProductHint[T](ConfigFieldMapping(CamelCase, SnakeCase))

  def load(): AppConfig = {
    val config: AppConfig = loadConfigOrThrow[AppConfig]
    config
  }
}
