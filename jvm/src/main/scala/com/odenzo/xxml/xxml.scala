package com.odenzo

import javax.xml.parsers.SAXParserFactory

/** For 2.13 backward compatability to import com.odenzo.xxml._ to get the implicits. */
package object xxml extends org.http4s.scalaxml.ElemInstances {
  override val saxFactory: SAXParserFactory = SAXParserFactory.newInstance
}
