package com.odenzo.xxml

import com.odenzo.xxml.{TestData, XXMLParser}

import scala.xml.*

class JVMSharedBasicTest extends munit.FunSuite {
  // Hmm. dunno how to debug test this in Node env.

  test("JVMSimpleSerialize") {
    val resultSXML: Elem = XXMLParser.parse(TestData.simpleSerialize)

    val xmlStr = XXMLParser.serialize(resultSXML)
    println(s"""| JVM Simple Input String: 
                | ${TestData.simpleSerialize}
                |  
                |  Parsed Elem (toString):
                |  ${resultSXML.toString()}
                |
                |  Serialized:
                |  $xmlStr
         """.stripMargin)

  }

}
