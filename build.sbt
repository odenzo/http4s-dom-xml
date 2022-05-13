import sbt.*
import MyCompileOptions.{optV2, optV3}
import org.scalajs.jsenv.nodejs.NodeJSEnv
import sbt.Keys.libraryDependencies
ThisBuild / scalaVersion       := "3.1.2"
ThisBuild / crossScalaVersions := List("3.1.2")

//ThisBuild / jsEnv := new NodeJSEnv()
//jsEnv             := new NodeJSEnv()

ThisBuild / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) } // Needed for NodeJS in Test at least?
scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) }             // Needed for NodeJS in Test at least?
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

// I thought this no longer needed, but testOnly works on classname only without it
ThisBuild / testFrameworks += new TestFramework("munit.Framework")

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
  // .jsEnablePlugins(ScalaJSBundlerPlugin)
  .jsSettings(
    // jsEnv                                  := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    jsEnv                                  := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    // jsEnv                                  := new NodeJSEnv(),
    // scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    Test / fork                            := false,
    //  libraryDependencies += "org.http4s"   %%% "http4s-dom"  % V.http4sDom, // Not sure I need this
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.1.0" // Shouldn't this be bundled into Node?
  )
  .jvmSettings(libraryDependencies ++= Seq("org.http4s" %% "http4s-scala-xml" % V.http4s))

addCommandAlias("to", "xxmlJS/testOnly -- --tests=")
