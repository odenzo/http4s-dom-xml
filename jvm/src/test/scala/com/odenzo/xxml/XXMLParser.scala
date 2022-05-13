package com.odenzo.xxml

import java.io.StringWriter
import scala.xml.dtd.DocType

/** Parses basic XML to ScalaXML. ScalaXML is x-platform, but the parser isn't. This is just a helper in case you are parsing manually
  * instead of using the build-in EntityDecoder/Encoder from HTTP4S on the JVM
  */
object XXMLParser extends XPlatformXMLParser {

  import javax.xml.parsers.SAXParserFactory
  import scala.xml.factory.XMLLoader
  import scala.xml.{Elem, XML}

  def customXML: XMLLoader[Elem] = XML.withSAXParser {
    val factory = SAXParserFactory.newInstance()
    factory.setFeature("http://xml.org/sax/features/validation", true)
    factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
    factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false)
    factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
    factory.newSAXParser()
  }

  def parse(s: String): Elem = {
    println("JVM Parsing")
    customXML.loadString(s) // A new one for each parse.
  }

  def serialize(elem: Elem, encoding: String = "UTF-8"): String = SXMLSerializer.serialize(elem, encoding)
}
