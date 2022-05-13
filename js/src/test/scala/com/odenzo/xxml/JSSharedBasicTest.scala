package com.odenzo.xxml

import com.odenzo.xxml.{TestData, XXMLParser}

import scala.xml.*

object codecs extends ElemInstances

class JSSharedBasicTest extends munit.FunSuite {
  // Hmm. dunno how to debug test this in Node env.

  test("SimpleSerialize".ignore) {
    val resultSXML: xml.Elem = XXMLParser.parse(TestData.simple)
    val xmlStr               = XXMLParser.serialize(resultSXML)
    println(s"Simple scala-xml:\n${resultSXML}")
    println(s"Simple scala-xml strign:\n$xmlStr")

  }

  val simpleElem: Elem = <root><something>InPrefScala</something></root>
  test("Encoder") {
    codecs.xxmlEncoder.toEntity(simpleElem)
  }

}
