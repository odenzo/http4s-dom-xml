package com.odenzo.xxml

import cats.effect.IO
import cats.effect.syntax.all.*
import com.odenzo.xxml.XXMLParser.xmlMimeType
import org.http4s.*
import org.http4s.client.*
import org.http4s.syntax.all.*
import org.scalajs.dom.{ProgressEvent, XMLHttpRequest}

/** An Attempt to read an XML file as text in ScalaJS NODEJS environment. Uctually if I have a URL maybe I can "fetch" it. */
class JSDomTest extends munit.CatsEffectSuite {

  private val xmlStr = """<root><happy/></root>"""
  import cats.effect.unsafe.implicits.global
  // fs2.io.file.Files[IO].currentWorkingDirectory.map(cwd => println(s"CWD $cwd"))
  /* Can't resolve fetch in JSONEnv even */
  test("JSDOM Based Testing") {
    println(s"JSDOM....")
    import org.scalajs.dom._
    import org.scalajs.dom
    val doc    = new org.scalajs.dom.DOMParser().parseFromString(xmlStr, org.scalajs.dom.MIMEType.`application/xml`)
    val elem   = doc.documentElement
    val XMLS   = new XMLSerializer()
    val docOut = XMLS.serializeToString(doc)

    println(s"Happy Elem: $docOut")
  }

  test("ScalaXML") {
    val elem = XXMLParser.parse(xmlStr)

  }

}
