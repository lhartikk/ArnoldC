package org.arnoldc

import org.parboiled.errors.ParsingException

class MethodTest extends ArnoldGeneratorTest {

  it should "evalute method other than main" in {
    val code =
      "LISTEN TO ME VERY CAREFULLY mymethod\n" +
        "HASTA LA VISTA, BABY\n" +
        "ITS SHOWTIME\n" +
        "TALK TO THE HAND \"Hello\"\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("Hello\n")
  }

  it should "evalute method other than main2" in {
    val code =
      "LISTEN TO ME VERY CAREFULLY mymethod\n" +
        "HASTA LA VISTA, BABY\n" +
        "ITS SHOWTIME\n" +
        "TALK TO THE HAND \"Hello\"\n" +
        "YOU HAVE BEEN TERMINATED"
    getOutput(code) should equal("Hello\n")
  }

  it should "evalute method other than main3" in {
    val code =
      "ITS SHOWTIME\n" +
        "TALK TO THE HAND \"Hello\"\n" +
        "YOU HAVE BEEN TERMINATED\n" +
        "LISTEN TO ME VERY CAREFULLY mymethod\n" +
        "HASTA LA VISTA, BABY\n"
    getOutput(code) should equal("Hello\n")
  }
  it should "evalute method other than main4" in {
    val code =
      "ITS SHOWTIME\n" +
        "TALK TO THE HAND \"Hello\"\n" +
        "YOU HAVE BEEN TERMINATED\n" +
        "LISTEN TO ME VERY CAREFULLY mymethod\n" +
        "HASTA LA VISTA, BABY"
    getOutput(code) should equal("Hello\n")
  }

  it should "evalute a plain method call" in {
    val code =
      "ITS SHOWTIME\n" +
        "DO IT NOW printHello\n" +
        "YOU HAVE BEEN TERMINATED\n" +
        "LISTEN TO ME VERY CAREFULLY printHello\n" +
        "TALK TO THE HAND \"Hello\"\n" +
        "HASTA LA VISTA, BABY"
    getOutput(code) should equal("Hello\n")
  }

  it should "evalute a method call that takes an argument" in {
    val code =
      "ITS SHOWTIME\n" +
        "HEY CHRISTMAS TREE argument\n" +
        "YOU SET US UP 123\n" +
        "DO IT NOW printInteger argument\n" +
        "YOU HAVE BEEN TERMINATED\n" +
        "LISTEN TO ME VERY CAREFULLY printInteger\n" +
        "I NEED YOUR CLOTHES YOUR BOOTS AND YOUR MOTORCYCLE value\n" +
        "TALK TO THE HAND value\n" +
        "HASTA LA VISTA, BABY"
    getOutput(code) should equal("123\n")
  }

  it should "evalute multiple method calls" in {
    val code =
      "ITS SHOWTIME\n" +
        "DO IT NOW printHello\n" +
        "DO IT NOW printCheers\n" +
        "YOU HAVE BEEN TERMINATED\n" +
        "LISTEN TO ME VERY CAREFULLY printHello\n" +
        "TALK TO THE HAND \"Hello\"\n" +
        "HASTA LA VISTA, BABY\n" +
        "LISTEN TO ME VERY CAREFULLY printCheers\n" +
        "TALK TO THE HAND \"Cheers\"\n" +
        "HASTA LA VISTA, BABY"
    getOutput(code) should equal("Hello\nCheers\n")
  }

  it should "evalute method calls inside method calls" in {
    val code =
      "ITS SHOWTIME\n" +
        "DO IT NOW printHello\n" +
        "YOU HAVE BEEN TERMINATED\n" +
        "LISTEN TO ME VERY CAREFULLY printHello\n" +
        "TALK TO THE HAND \"Hello\"\n" +
        "DO IT NOW printCheers\n" +
        "DO IT NOW printHejsan\n" +
        "HASTA LA VISTA, BABY\n" +
        "LISTEN TO ME VERY CAREFULLY printCheers\n" +
        "TALK TO THE HAND \"Cheers\"\n" +
        "HASTA LA VISTA, BABY\n" +
        "LISTEN TO ME VERY CAREFULLY printHejsan\n" +
        "TALK TO THE HAND \"Hejsan\"\n" +
        "HASTA LA VISTA, BABY"
    getOutput(code) should equal("Hello\nCheers\nHejsan\n")
  }

  it should "detect unclosed main method" in {
    val code =
      "ITS SHOWTIME\n" +
        "LISTEN TO ME VERY CAREFULLY printHello\n" +
        "TALK TO THE HAND \"Hello\"\n"
    intercept[ParsingException] {
      getOutput(code)
    }
  }
  it should "detect unclosed methods" in {
    val code =
      "ITS SHOWTIME\n" +
        "YOU HAVE BEEN TERMINATED\n" +
        "LISTEN TO ME VERY CAREFULLY printHello\n" +
        "TALK TO THE HAND \"Hello\"\n"
    intercept[ParsingException] {
      getOutput(code)
    }
  }

  it should "detect calls to methods that are not declared" in {
    val code =
      "ITS SHOWTIME\n" +
        "DO IT NOW noSuchMethod\n" +
        "YOU HAVE BEEN TERMINATED\n"
    intercept[ParsingException] {
      getOutput(code)
    }
  }


}
