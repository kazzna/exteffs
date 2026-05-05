ThisBuild / organization := "jp.kazzna"
ThisBuild / scalaVersion := "3.8.3"

ThisBuild / publishTo := sys.env.get("GITHUB_PACKAGES_MAVEN_URL").map { url =>
  "GitHub Package Registry" at url
}
ThisBuild / credentials += Credentials(Path.userHome / ".sbt" / "1.0" / "ghpackages.credentials")

lazy val root = (project in file("."))
  .settings(
    name := "exteffs",
    version := "0.1.0-SNAPSHOT",
    scalacOptions ++= Seq(
      "--deprecation",
      "--feature"
    ),
    libraryDependencies ++= Seq(
      "jp.kazzna" %% "types" % "0.1.0",
      "org.scalatest" %% "scalatest" % "3.2.15" % "test"
    ),
    Test / testOptions += Tests.Argument("-l", "org.scalatest.tags.Slow")
  )

resolvers += "GitHub Packages kazzna/types" at "https://maven.pkg.github.com/kazzna/types"
