package com.odenzo.xxml

import cats.effect.IO
import cats.effect.syntax.all.*
//import org.http4s.*
//import org.http4s.client.*
//import org.http4s.syntax.all.*
import org.scalajs.dom.XMLHttpRequest
import org.scalajs.dom.ProgressEvent

/** An Attempt to read an XML file as text in ScalaJS NODEJS environment. Uctually if I have a URL maybe I can "fetch" it. */
class LoadFileTest extends munit.CatsEffectSuite {

//  val url = uri"file:///Users/stevef/IJ.env"
//  import cats.effect.unsafe.implicits.global
//  // fs2.io.file.Files[IO].currentWorkingDirectory.map(cwd => println(s"CWD $cwd"))
//  /* Can't resolve fetch in JSONEnv even */
  test("Loading File") {
    IO(println(s"Loading File"))
//    val client: Client[IO] = org.http4s.dom.FetchClientBuilder[IO].create
//    val rq: Request[IO]    = Request[IO](Method.GET, url)
//    val res: IO[String]    = client.expect[String](rq)
//    res.flatTap(txt => IO(println(s"XML: $txt")))
  }

}
