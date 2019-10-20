import BuildSettings._

lazy val StockMan = project
  .in(file("."))
  .settings(basicSettings)
  .settings(
    libraryDependencies ++=
      Dependencies.compileDeps ++
        Dependencies.testDeps
  )

//Set ttl of snapshots so they always refresh
coursierTtl := None

coverageOutputCobertura := false
coverageOutputXML := false
coverageOutputDebug := true

coverageExcludedPackages := Seq(
  "$:",
  ".*app.*",
  ".*config.*",
  ".*models.*",
  ".*errors.*",
  ".*PortfolioDocument.*",
  ".*Codec.*",
  ".*Static.*"
).mkString(";")

addCommandAlias("compileAll", ";compile;test:compile")
