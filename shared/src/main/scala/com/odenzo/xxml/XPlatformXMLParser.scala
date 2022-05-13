package com.odenzo.xxml

import scala.xml.Elem

trait XPlatformXMLParser {
  def parse(s: String): Elem
  def serialize(elem: Elem, encoding: String = "UTF-8"): String
}
