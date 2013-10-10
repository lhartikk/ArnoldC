package org.arnoldc

import org.scalatest.FlatSpec
import org.parboiled.errors.ParsingException
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.BeforeAndAfter

class ArnoldParserTest extends FlatSpec with BeforeAndAfter with ShouldMatchers {


  var arnoldParser = new ArnoldParser



  "ArnoldParser" should "parse when given only BEGIN and END commands" in {
    val input =
      "ITS SHOWTIME\n" +
        "YOU HAVE BEEN TERMINATED\n"
    arnoldParser.parse(input)
  }

  it should "detect if the END command is not present" in {
    val input =
      "ITS SHOWTIME\n"
    intercept[ParsingException] {
      arnoldParser.parse(input)
    }
  }

  it should "parse when simple variable declaration is used" in {
    val input =
      "ITS SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 123\n" +
        "YOU HAVE BEEN TERMINATED\n"
    arnoldParser.parse(input)
  }

  it should "detect faulty variable names" in {
    val input =
      "ITS SHOWTIME\n" +
        "HEY CHRISTMAS TREE 1VAR\n" +
        "YOU SET US UP 123\n" +
        "YOU HAVE BEEN TERMINATED\n"
    intercept[ParsingException] {
      arnoldParser.parse(input)
    }
  }

  it should "parse when printing a variable" in {
    val input =
      "ITS SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 123\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    arnoldParser.parse(input)
  }

  it should "parse when incrementing a variable" in {
    val input =
      "ITS SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "GET UP 44\n" +
        "ENOUGH TALK\n" +
        "YOU HAVE BEEN TERMINATED\n"

    arnoldParser.parse(input)
  }

  it should "parse when decrementing a variable" in {
    val input =
      "ITS SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "GET DOWN 44\n" +
        "ENOUGH TALK\n" +
        "YOU HAVE BEEN TERMINATED\n"

    arnoldParser.parse(input)
  }

  it should "parse when printing two variables" in {
    val input: String =
      "ITS SHOWTIME\n" +
        "HEY CHRISTMAS TREE A\n" +
        "YOU SET US UP 999\n" +
        "HEY CHRISTMAS TREE B\n" +
        "YOU SET US UP 555\n" +
        "TALK TO THE HAND A\n" +
        "TALK TO THE HAND B\n" +
        "YOU HAVE BEEN TERMINATED\n"


    val parsed =
      "RootNode(List(DeclareVariableNode(A,NumberNode(999))," +
        " DeclareVariableNode(B,NumberNode(555))" +
        ", PrintNode(VariableNode(A)), PrintNode(VariableNode(B))))"
    parsed should equal(arnoldParser.parse(input).toString)
  }

  it should "function when a variable is incremented and printed" in {
    val code =
      "ITS SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "GET UP 44\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    println(arnoldParser.parse(code))
  }

}