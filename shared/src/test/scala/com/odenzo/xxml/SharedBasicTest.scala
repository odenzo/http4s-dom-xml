package com.odenzo.xxml

import com.odenzo.xxml.{TestData, XXMLParser}

import scala.xml.Elem

class SharedBasicTest extends munit.FunSuite {
  // Hmm. dunno how to debug test this in Node env.
  // This a troublle for IJ, new JSObject for scala object even, but no new in Scala.
  val stParser: XPlatformXMLParser = XXMLParser

  test("SimpleSerialize") {
    val data             = TestData.simpleSerialize
    val resultSXML: Elem = stParser.parse(data)
    val xmlStr: String   = stParser.serialize(resultSXML)
    println(s"""| SHARED Simple Input String: 
                | ${data}
                |  
                |  Parsed Elem (toString):
                |  ${resultSXML.toString()}
                |
                |  Serialized:
                |  $xmlStr
             """.stripMargin)

  }

}
