package org.arnoldc

import org.parboiled.errors.ParsingException

class ArithmeticTest extends ArnoldGeneratorTest {

  it should "function when a variable is declared" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 123\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("")
  }

  it should "function when an integer is printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "TALK TO THE HAND 123\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("123\n")
  }

  it should "evaluate when a negative integer is printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "TALK TO THE HAND -111\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-111\n")
  }

  it should "evaluate when a 'boolean' is printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE varfalse\n" +
        "YOU SET US UP @I LIED\n" +
        "TALK TO THE HAND varfalse\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("0\n")
  }

  it should "evaluate when a string is printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "TALK TO THE HAND \"this should be printed\"\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("this should be printed\n")
  }

  it should "evaluate when a more exotic string is printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "TALK TO THE HAND \"!!! ??? äöäöäöä@#0123=+-,.\"\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("!!! ??? äöäöäöä@#0123=+-,.\n")
  }

  it should "evaluate when an integer is declared and printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE A\n" +
        "YOU SET US UP 999\n" +
        "HEY CHRISTMAS TREE B\n" +
        "YOU SET US UP 555\n" +
        "TALK TO THE HAND A\n" +
        "TALK TO THE HAND B\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("999\n555\n")
  }
  it should "evaluate when a negative integer is declared and printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE a\n" +
        "YOU SET US UP -999\n" +
        "HEY CHRISTMAS TREE b\n" +
        "YOU SET US UP -555\n" +
        "TALK TO THE HAND a\n" +
        "TALK TO THE HAND b\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-999\n-555\n")
  }

  it should "evaluate when assigning a variable" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION 123\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"

    getOutput(code) should equal("123\n")
  }


  it should "evaluate when assigning multiple variables " in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 22\n" +
        "HEY CHRISTMAS TREE var2\n" +
        "YOU SET US UP 27\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION 123\n" +
        "ENOUGH TALK\n" +
        "GET TO THE CHOPPER var2\n" +
        "HERE IS MY INVITATION 707\n" +
        "ENOUGH TALK\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION var2\n" +
        "GET UP var\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"

    getOutput(code) should equal("830\n")
  }

  it should "evaluate when an integer is incremented and printed" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "GET UP 44\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("66\n")
  }

  it should "evaluate when an integer is decremented and printed" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "GET DOWN 44\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-22\n")
  }

  it should "evaluate when an integer is decremented with a negative value" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "GET DOWN -44\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("66\n")
  }


  it should "evaluate when an integer is incremented with a negative value" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "GET UP -44\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-22\n")
  }

  it should "evaluate when multiplying variables" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "YOU'RE FIRED 13\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("286\n")
  }

  it should "evaluate when multiplying variables with different signs" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "YOU'RE FIRED -13\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-286\n")
  }

  it should "evaluate when multiplying variables with zero" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "YOU'RE FIRED 0\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("0\n")
  }

  it should "evaluate when multiplying assigned variables" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 7\n" +
        "HEY CHRISTMAS TREE VAR2\n" +
        "YOU SET US UP 4\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "YOU'RE FIRED VAR2\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("28\n")
  }

  it should "evaluate when dividing variables" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 100\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "HE HAD TO SPLIT 4\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("25\n")
  }

  it should "evaluate when dividing variables with different signs" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 99\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "HE HAD TO SPLIT -33\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-3\n")
  }

  it should "evaluate when dividing variables with one" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "HE HAD TO SPLIT 1\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("22\n")
  }

  it should "evaluate when dividing assigned variables" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 9\n" +
        "HEY CHRISTMAS TREE VAR2\n" +
        "YOU SET US UP 4\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION VAR\n" +
        "HE HAD TO SPLIT VAR2\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("2\n")
  }

  it should "evaluate using different arithmetic operations" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION 11\n" +
        "GET DOWN 43\n" +
        "GET UP 54\n" +
        "GET UP 44\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("66\n")
  }

  it should "evaluate using different arithmetic operations vol2" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION 11\n" +
        "GET DOWN 55\n" +
        "GET UP 11\n" +
        "GET UP 22\n" +
        "GET UP 23\n" +
        "GET DOWN 0\n" +
        "GET UP 0\n" +
        "GET UP 1\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("13\n")
  }

  it should "evaluate using different arithmetic operations vol3" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "GET TO THE CHOPPER VAR\n" +
        "HERE IS MY INVITATION 11\n" +
        "GET DOWN 22\n" +
        "HE HAD TO SPLIT -11\n" +
        "YOU'RE FIRED 23\n" +
        "GET UP 23\n" +
        "GET DOWN 22\n" +
        "HE HAD TO SPLIT 2\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND VAR\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("12\n")
  }

  it should "detect duplicate variable declarations" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "HEY CHRISTMAS TREE VAR\n" +
        "YOU SET US UP 22\n" +
        "YOU HAVE BEEN TERMINATED\n"
    intercept[ParsingException] {
      getOutput(code)
    }
  }

  it should "detect faulty variable names" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE 1VAR\n" +
        "YOU SET US UP 123\n" +
        "YOU HAVE BEEN TERMINATED\n"
    intercept[ParsingException] {
      getOutput(code)
    }
  }
}
