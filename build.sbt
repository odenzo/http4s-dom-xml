import sbt.*
import Libs.*

import MyCompileOptions.{optV2, optV3}
import sbt.Keys.libraryDependencies
ThisBuild / scalaVersion := "2.13.8"
lazy val supportedScalaVersions = List("2.13.8", "3.1.2")
val javart                      = "1.11"

ThisBuild / versionScheme    := Some("semver-spec")
ThisBuild / githubOwner      := "odenzo"
ThisBuild / githubRepository := "http4s-dom-xml"

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
  .settings(name := "http4s-dom-project", crossScalaVersions := supportedScalaVersions, doc / aggregate := false, publish / skip := true)

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
