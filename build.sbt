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

addCommandAlias("compileAll", ";compile;test:compile")