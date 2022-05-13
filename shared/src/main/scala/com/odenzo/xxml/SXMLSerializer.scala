package com.odenzo.xxml

import java.io.StringWriter
import scala.xml.dtd.DocType

object SXMLSerializer {
  def serialize(elem: scala.xml.Elem, encoding: String = "UTF-8", withDecl: Boolean = true): String = {
    val writer           = new StringWriter(1024)
    val docType: DocType = null
    scala.xml.XML.write(writer, elem, encoding, withDecl, docType)
    writer.flush()
    writer.close()
    writer.toString
  }
}
