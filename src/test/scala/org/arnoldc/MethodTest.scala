package org.arnoldc

import org.parboiled.errors.ParsingException

class MethodTest extends ArnoldGeneratorTest {

  it should "evalute method other than main" in {
    val code =
      "LISTEN TO ME VERY CAREFULLY mymethod\n" +
        "THE HELL WITH YOU\n" +
        "ITS SHOWTIME\n" +
        "TALK TO THE HAND \"Hello\"\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("Hello\n")
  }

  it should "evalute method other than main2" in {
    val code =
      "LISTEN TO ME VERY CAREFULLY mymethod\n" +
        "THE HELL WITH YOU\n" +
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
        "THE HELL WITH YOU\n"
    getOutput(code) should equal("Hello\n")
  }
  it should "evalute method other than main4" in {
    val code =
      "ITS SHOWTIME\n" +
        "TALK TO THE HAND \"Hello\"\n" +
        "YOU HAVE BEEN TERMINATED\n" +
        "LISTEN TO ME VERY CAREFULLY mymethod\n" +
        "THE HELL WITH YOU"
    getOutput(code) should equal("Hello\n")
  }

  it should "evalute method call" in {
    val code =
      "ITS SHOWTIME\n" +
        "DO IT NOW printHello\n" +
        "YOU HAVE BEEN TERMINATED\n" +
        "LISTEN TO ME VERY CAREFULLY printHello\n" +
        "TALK TO THE HAND \"Hello\"\n" +
        "THE HELL WITH YOU"
    getOutput(code) should equal("Hello\n")
  }
  it should "evalute multiple method calls" in {
    val code =
      "ITS SHOWTIME\n" +
        "DO IT NOW printHello\n" +
        "DO IT NOW printCheers\n" +
        "YOU HAVE BEEN TERMINATED\n" +
        "LISTEN TO ME VERY CAREFULLY printHello\n" +
        "TALK TO THE HAND \"Hello\"\n" +
        "THE HELL WITH YOU\n" +
        "LISTEN TO ME VERY CAREFULLY printCheers\n" +
        "TALK TO THE HAND \"Cheers\"\n" +
        "THE HELL WITH YOU"
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
        "THE HELL WITH YOU\n" +
        "LISTEN TO ME VERY CAREFULLY printCheers\n" +
        "TALK TO THE HAND \"Cheers\"\n" +
        "THE HELL WITH YOU\n" +
        "LISTEN TO ME VERY CAREFULLY printHejsan\n" +
        "TALK TO THE HAND \"Hejsan\"\n" +
        "THE HELL WITH YOU"
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


}
