package com.odenzo.xxml

import org.scalajs.dom
import org.scalajs.dom.XMLSerializer

/** Once in scala.xml Elem form we can serialize, this is to attempt to serialize the Browser DOM form instead of scala.xml */
object DOMParserSerialize {

  def serialize(doc: dom.Document): String = {
    val xs = new XMLSerializer
    xs.serializeToString(doc)
  }

}
