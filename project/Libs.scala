import sbt.{Def, _}
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._

object V {
  val airstream          = "0.14.2"
  val blindSightLog      = "1.4.0"
  val catsEffect         = "3.3.11"
  val catsMice           = "1.0.10"
  val catsRetry          = "3.1.0"
  val catsParse          = "0.3.6"
  val cats               = "2.7.0"
  val catsCollections    = "0.9.3"
  val circeGenericExtras = "0.15.0-M1"
  val circeOptics        = "0.15.0-M1"
  val circe              = "0.15.0-M1"
  val doobie             = "1.0.0-RC2"
  val fs2                = "3.2.7"
  val fs2Data            = "1.3.1"
  val http4s             = "1.0.0-M32"
  val http4sDom          = "1.0.0-M32"
  val logback            = "1.2.11"
  val monocle            = "3.1.0"
  val munitCats          = "1.0.7"
  val munit              = "1.0.0-M3"
  val oslib              = "0.8.1"
  val pprint             = "0.7.3"
  val scalaXML           = "2.1.0"
  val scodecBits         = "1.1.30"
  val scodecCats         = "1.0.0"
  val scodec             = "1.11.7"
  val scribe             = "3.8.2"
  val scribeCats         = "3.8.2"
  val squants            = "1.7.0"
  val sttpClient         = "3.5.1"
  val tapir              = "0.20.1"
  val weaverTest         = "0.7.11"
  val laminar            = "0.14.2" // "0.13.10"
  val laminarExt         = "0.14.2"
  val scalaJavaTime      = "2.3.0"
}

//noinspection TypeAnnotation
object Libs {

  val testing =
    Seq("org.scalameta" %% "munit" % V.munit % Test, "org.typelevel" %% "munit-cats-effect-3" % V.munitCats % Test)

  val cats = Seq("org.typelevel" %% "cats-core" % V.cats, "org.typelevel" %% "cats-effect" % V.catsEffect)

  val fs2 = Seq("co.fs2" %% "fs2-core" % V.fs2, "co.fs2" %% "fs2-io" % V.fs2)

  lazy val libs_js_stubs = Seq("org.scala-js" %% "scalajs-stubs" % "1.1.0" % "provided")

  val monocle = Seq("dev.optics" %% "monocle-core" % V.monocle, "dev.optics" %% "monocle-macro" % V.monocle)

  // This IS NOW supposed to work with ScalaJS
  val scalaXML = Seq("org.scala-lang.modules" %% "scala-xml" % V.scalaXML)

  val sttpClient = Seq("com.softwaremill.sttp.client3" %% "core" % V.sttpClient, "com.softwaremill.sttp.client3" %% "circe" % V.sttpClient)

  val scribe      = Seq("com.outr" %% "scribe" % V.scribe)
  val scribeSLF4J = Seq("com.outr" %% "scribe-slf4j" % V.scribe)
  val scribeCats  = Seq("com.outr" %% "scribe-cats" % V.scribeCats)
  val logback     = Seq("ch.qos.logback" % "logback-classic" % V.logback)

  /** HTTP4S some parts are backend only */
  val http4s = Seq(
    "org.http4s" %% "http4s-dsl"          % V.http4s,
    "org.http4s" %% "http4s-ember-server" % V.http4s,
    "org.http4s" %% "http4s-ember-client" % V.http4s,
    "org.http4s" %% "http4s-circe"        % V.http4s,
    "org.http4s" %% "http4s-scala-xml"    % V.http4s
  )

  val weaverTest = Seq("com.disneystreaming" %% "weaver-cats" % V.weaverTest % Test)

}

object XLib {
//
//  import OModules.OModule
//
//  lazy val weaverTest = Def.setting("com.disneystreaming" %% "weaver-cats" % V.weaverTest % Test)
//  // Needed in sbt I thnk
//  //  testFrameworks += new TestFramework("weaver.framework.CatsEffect")

  lazy val scalaXML        = Def.setting("org.scala-lang.modules" %%% "scala-xml" % V.scalaXML)
  lazy val cats            = Def.setting("org.typelevel" %%% "cats-core" % V.cats)
  lazy val catsEffect      = Def.setting("org.typelevel" %%% "cats-effect" % V.catsEffect)
  lazy val catsRetry       = Def.setting("com.github.cb372" %%% "cats-retry" % V.catsRetry)
  lazy val catsParse       = Def.setting("org.typelevel" %%% "cats-parse" % V.catsParse)
  lazy val catsCollections = Def.setting("org.typelevel" %%% "cats-collections-core" % V.catsCollections)
  lazy val monocle         = Def.setting("dev.optics" %%% "monocle-core" % V.monocle)
  lazy val pprint          = Def.setting("com.lihaoyi" %%% "pprint" % V.pprint)
  lazy val scribe          = Def.setting("com.outr" %%% "scribe" % V.scribe)
  lazy val scribeSLF       = Def.setting("com.outr" %%% "scribe-sfl4j" % V.scribe)
  lazy val munit           = Def.setting("org.scalameta" %%% "munit" % V.munit % Test)
  lazy val scodecBits      = Def.setting("org.scodec" %%% "scodec-bits" % V.scodecBits)
  lazy val munitCats       = Def.setting("org.typelevel" %%% "munit-cats-effect-3" % V.munitCats % Test)

  lazy val http4sEmber = Def.setting("org.http4s" %%% "http4s-ember-client" % V.http4s) // Diff with Blaze, use different on JVM or JS
  lazy val http4sXml   = Def.setting("org.http4s" %%% "http4s-scala-xml" % V.http4s)
  lazy val http4sCore  = Def.setting("org.http4s" %%% "http4s-core" % V.http4s)

  val fs2   = Def.setting("co.fs2" %%% "fs2-core" % V.fs2)
  val fs2IO = Def.setting("co.fs2" %%% "fs2-io" % V.fs2)

  val scalaJavaTime     = Def.setting("io.github.cquiroz" %%% "scala-java-time" % V.scalaJavaTime)
  val scalaJavaTimeTZDB = Def.setting("io.github.cquiroz" %%% "scala-java-time-tzdb" % V.scalaJavaTime)

}

object JSLibs {}
