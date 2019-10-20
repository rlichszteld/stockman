import sbt.Keys._
import sbt._

object BuildSettings {

  lazy val basicSettings = Seq(
    name := "StockMan",
    version := "1.0",
    homepage := Some(new URL("https://github.com/rlichszteld/stockman")),
    description := "Simple, lightweight stock manager",
    scalaVersion := "2.12.5",
    resolvers ++= Dependencies.resolutionRepos,
    scalacOptions := Seq(
      "-encoding",
      "utf8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-target:jvm-1.8",
      "-language:_",
      "-Ywarn-adapted-args",
      "-Ypartial-unification"
    )
  )
}
