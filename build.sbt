import sbt.*
import MyCompileOptions.{optV2, optV3}
import sbt.Keys.libraryDependencies
ThisBuild / scalaVersion       := "3.1.2"
ThisBuild / crossScalaVersions := List("3.1.2", "2.13.8")

val javart = "1.11"

ThisBuild / versionScheme     := Some("semver-spec")
credentials += Credentials(Path.userHome / ".sbt" / "1.0" / "github.sbt")
//ThisBuild / githubOwner       := "odenzo"
//ThisBuild / githubRepository  := "http4s-dom-xml"
//ThisBuild / githubTokenSource := TokenSource.GitConfig("github.token")
//ThisBuild / pomIncludeRepository := (_ => false)
ThisBuild / publishMavenStyle := true
ThisBuild / organization      := "com.odenzo"
val gitHubMaven: MavenRepository = ("GitHub Package Registry" at "https://maven.pkg.github.com/odenzo/http4s-dom-xml")

// Because PublishM2 is writing the POM twice under the Java branch. Guess it doesn't like a single root project?
//  [error] stack trace is suppressed; run last xxmlJS / publishM2 for the full output
//  [error] stack trace is suppressed; run last xxmlJVM / publishM2 for the full output
//  [error] stack trace is suppressed; run last publishM2 for the full output
// Beta bugs, or am I suppose to supress publishing in cross-platform shared code.
ThisBuild / publishConfiguration      := publishConfiguration.value.withOverwrite(true)
ThisBuild / publishM2Configuration    := publishM2Configuration.value.withOverwrite(true)
ThisBuild / publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)

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

lazy val xxml = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("."))
  .settings(
    name := "http4s-dom-xml",
    libraryDependencies ++= Seq(
      XLib.cats.value,
      XLib.catsEffect.value,
      XLib.scalaXML.value,
      XLib.http4sCore.value, // Definatelty need this.
      "org.typelevel" %%% "munit-cats-effect-3" % "1.0.7" % Test
    )
  )
  .jsSettings(
    jsEnv                                  := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    Test / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.NoModule) },
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    Test / fork                            := false, // Required
    // libraryDependencies += "org.http4s"   %%% "http4s-dom"  % V.http4sDom, // Not sure I need this
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.1.0"
  )
  .jvmSettings(libraryDependencies ++= Seq("org.http4s" %% "http4s-scala-xml" % V.http4s))

addCommandAlias("to", "xxmlJS/testOnly -- --tests=")
