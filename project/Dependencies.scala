import sbt.{ModuleID, _}

object Dependencies {

  val resolutionRepos: Seq[MavenRepository] = Seq(
    Resolver.mavenLocal,
    Resolver.sonatypeRepo("snapshots"),
    "Flyway" at "https://flywaydb.org/repo",
    Resolver.bintrayRepo("streetcontxt", "maven")
  )

  def provided(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")
  def test(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")
  def runtime(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "runtime")
  def container(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "container")

  lazy val compileDeps: Seq[ModuleID] = Seq(
    akkaActors,
    logbackClassic,
    scalaLogging,
    pureConfig,
    cats,
    akkaHttpCirce,
    mongoDB,
    mongoCodecs,
    // -- potentially to scrap deps below
    mySql,
    slick,
    slickHikaricp
  ) ++ circe

  lazy val testDeps: Seq[ModuleID] = Seq(
    slickTestKit,
    scalaTest,
    akkaHttpTestKit,
    akkaStreamTestKit,
    scalaMock
  )

  private object Version {
    val akka: String = "2.5.25"
    val akkaHttp: String = "10.1.10"
    val logBack: String = "1.2.3"
    val scalaLogging: String = "3.7.2"
    val pureconfig: String = "0.10.1"
    val mysql: String = "8.0.11"
    val slick: String = "3.2.1"
    val scalaTest: String = "3.0.5"
    val mock: String = "3.6.0"
    val circe = "0.12.2"
    val akkaHttpCirce = "1.29.1"
    val mongoDB = "2.7.0"
  }

  val akkaActors = "com.typesafe.akka" %% "akka-actor" % Version.akka

  val logbackClassic: ModuleID = "ch.qos.logback" % "logback-classic" % Version.logBack
  val pureConfig: ModuleID = "com.github.pureconfig" %% "pureconfig" % Version.pureconfig

  val mySql: ModuleID = "mysql" % "mysql-connector-java" % Version.mysql
  val slick: ModuleID = "com.typesafe.slick" %% "slick" % Version.slick
  val slickHikaricp: ModuleID = "com.typesafe.slick" %% "slick-hikaricp" % Version.slick
  val slickTestKit: ModuleID = "com.typesafe.slick" %% "slick-testkit" % Version.slick % Test

  val cats: ModuleID = "org.typelevel" %% "cats-core" % "1.0.1"

  val mongoDB: ModuleID = "org.mongodb.scala" %% "mongo-scala-driver" % Version.mongoDB
  val mongoCodecs: ModuleID = "ch.rasc" % "bsoncodec" % "1.0.1"

  // test libs
  val scalaxml: ModuleID = "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
  val akkaHttpTestKit: ModuleID = "com.typesafe.akka" %% "akka-http-testkit" % Version.akkaHttp
  val akkaStreamTestKit = "com.typesafe.akka" %% "akka-stream-testkit" % Version.akka
  val akkaActorsTestKit = "com.typesafe.akka" %% "akka-testkit" % Version.akka
  val akkaTestkit: ModuleID = "com.typesafe.akka" %% "akka-testkit" % Version.akka

  val scalaMock: ModuleID = "org.scalamock" %% "scalamock-scalatest-support" % Version.mock excludeAll (
    ExclusionRule(organization = "org.scala-lang"),
    ExclusionRule(organization = "org.scala-lang.modules")
  )

  val jUnitInterface: ModuleID = "com.novocode" % "junit-interface" % "0.11" excludeAll (
    ExclusionRule("org.hamcrest", "hamcrest-core"),
    ExclusionRule("junit", "junit")
  )

  val scalaLogging: ModuleID = "com.typesafe.scala-logging" %% "scala-logging" % Version.scalaLogging
  val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % Version.scalaTest % Test

  val dependencyOverrides: Seq[ModuleID] = Seq()

  // clean list
  val circe: Seq[ModuleID] = Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % Version.circe)

  val akkaHttpCirce = "de.heikoseeberger" %% "akka-http-circe" % Version.akkaHttpCirce
}
