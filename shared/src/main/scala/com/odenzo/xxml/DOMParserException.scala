package com.odenzo.xxml

import scala.xml.dtd.DocType

/** This is exposed as part of errors from XXMLParser */
case class DOMParserException(message: String, cause: Throwable) extends Throwable(message, cause)

object DOMParserException {
  def apply(msg: String): DOMParserException = DOMParserException(msg, null)
}
