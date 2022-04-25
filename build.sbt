import sbt._

import MyCompileOptions.{optV2, optV3}
import sbt.Keys.libraryDependencies
ThisBuild / scalaVersion       := "2.13.8"
ThisBuild / crossScalaVersions := List("2.13.8", "3.1.2")
val javart = "1.11"

ThisBuild / versionScheme        := Some("semver-spec")
credentials += Credentials(Path.userHome / ".sbt" / "1.0" / "github.sbt")
//ThisBuild / githubOwner       := "odenzo"
//ThisBuild / githubRepository  := "http4s-dom-xml"
//ThisBuild / githubTokenSource := TokenSource.GitConfig("github.token")
ThisBuild / pomIncludeRepository := (_ => false)
ThisBuild / publishMavenStyle    := true
ThisBuild / organization         := "com.odenzo"
val gitHubMaven: MavenRepository = ("GitHub Package Registry" at "https://maven.pkg.github.com/odenzo/http4s-dom-xml")

ThisBuild / publishTo := Some(gitHubMaven)
ThisBuild / resolvers += gitHubMaven

inThisBuild {
  resolvers += Resolver.mavenLocal
  publishMavenStyle           := true
  bspEnabled                  := false
  organization                := "com.odenzo"
  Test / fork                 := false // ScalaJS can't be forked
  Test / parallelExecution    := false
  Test / logBuffered          := true
  Compile / parallelExecution := false
  scalacOptions               :=
    (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => optV3
      case _            => optV2
    })

  scalacOptions ++= Seq("-release", "11")
}
lazy val root = project
  .in(file("."))
  .aggregate(xml.jvm, xml.js)
  .settings(name := "http4s-dom-project", doc / aggregate := false, publish / skip := true)

lazy val xml = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("modules/xml-lib"))
  .settings(
    name := "http4s-dom-xml",
    libraryDependencies ++= Seq(XLib.cats.value, XLib.scalaXML.value, XLib.munit.value, XLib.http4sCore.value)
  )
  .jsSettings(
    libraryDependencies ++= Seq("org.scala-js" %%% "scalajs-dom" % "2.1.0"),
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  )

addCommandAlias("to", "xmlJS/testOnly -- --tests=")
