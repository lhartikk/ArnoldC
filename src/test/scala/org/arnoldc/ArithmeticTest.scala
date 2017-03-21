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

  it should "function when a variable is declared with keyword name" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE while\n" +
        "YOU SET US UP 123\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("")
  }

  it should "function when a variable is declared with keyword name vol2" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE CHILL\n" +
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

  it should "evaluate when a string with newlines is printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "TALK TO THE HAND \"line1\nline2\nline3\n\n\"\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("line1\nline2\nline3\n\n\n")
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
  it should "evaluate when dividing to fraction and then multiplying back" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 1\n" +
        "TALK TO THE HAND var\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION var\n" +
        "HE HAD TO SPLIT 2\n" +
        "YOU'RE FIRED 2\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("1\n0\n")
  }

  it should "evaluate when calculating modulo variables vol1" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 1\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION var\n" +
        "I LET HIM GO 2\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("1\n")
  }

  it should "evaluate when calculating modulo variables vol2" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 2\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION var\n" +
        "I LET HIM GO 2\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("0\n")
  }

    it should "evaluate when calculating modulo variables with negative value" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP -3\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION var\n" +
        "I LET HIM GO 2\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-1\n")
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

  it should "evaluate short max value on declaration" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 32767\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("32767\n")
  }

  it should "evaluate short min value on declaration" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP -32768\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-32768\n")
  }

  it should "evaluate short overflow on declaration" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 32768\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-32768\n")
  }

  it should "evaluate short overflow on declaration vol2" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 65537\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("1\n")
  }

  it should "evaluate short underflow on declaration" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP -32769\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("32767\n")
  }

  it should "evaluate short underflow on declaration vol2" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP -65538\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-2\n")
  }

  it should "evaluate short overflow on addition" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 32767\n" +
        "TALK TO THE HAND var\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION var\n" +
        "GET UP 1\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("32767\n32768\n")
  }

  it should "evaluate short overflow on addition vol2" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 32767\n" +
        "TALK TO THE HAND var\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION var\n" +
        "GET UP 32767\n" +
        "GET UP 32767\n" +
        "GET UP 32767\n" +
        "GET UP 32767\n" +
        "GET UP 32767\n" +
        "GET UP 32767\n" +
        "GET UP 32767\n" +
        "GET UP 32767\n" +
        "GET UP 32767\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("32767\n327670\n")
  }

  it should "evaluate short overflow on multiplication" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 32767\n" +
        "TALK TO THE HAND var\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION var\n" +
        "YOU'RE FIRED 32767\n" +
        "YOU'RE FIRED 32767\n" +
        "YOU'RE FIRED 32767\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("32767\n2147352577\n")
  }
  it should "evaluate short overflow on multiplication vol2" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP 32767\n" +
        "TALK TO THE HAND var\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION var\n" +
        "YOU'RE FIRED 32767\n" +
        "YOU'RE FIRED 32767\n" +
        "YOU'RE FIRED 32767\n" +
        "YOU'RE FIRED 32767\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("32767\n-2147319809\n")
  }
  it should "evaluate short underflow on multiplication" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP -32768\n" +
        "TALK TO THE HAND var\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION var\n" +
        "YOU'RE FIRED 32767\n" +
        "YOU'RE FIRED 32767\n" +
        "YOU'RE FIRED 32767\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-32768\n1073774592\n")
  }
  it should "evaluate short underflow on multiplication vol2" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP -32768\n" +
        "TALK TO THE HAND var\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION var\n" +
        "YOU'RE FIRED 32767\n" +
        "YOU'RE FIRED 32767\n" +
        "YOU'RE FIRED 32767\n" +
        "YOU'RE FIRED 32767\n" +
        "YOU'RE FIRED 32767\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-32768\n-1073709056\n")
  }

  it should "detect when a string with quote escaped quotes is printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "TALK TO THE HAND \"he said \"\"Hello\"\"\"\n" +
        "YOU HAVE BEEN TERMINATED\n"
    intercept[ParsingException] {
      getOutput(code)
    }
  }

  it should "detect when a string with backslash escaped quotes is printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "TALK TO THE HAND \"he said \\\"Hello\\\"\"\n" +
        "YOU HAVE BEEN TERMINATED\n"
    intercept[ParsingException] {
      getOutput(code)
    }
  }

  it should "detect when a string with backslashes is printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "TALK TO THE HAND \"C:\\Temp\\readme.txt\"\n" +
        "YOU HAVE BEEN TERMINATED\n"
    intercept[ParsingException] {
      getOutput(code)
    }
  }

  it should "detect when a string with backslash escaped backslashes is printed" in {
    val code: String =
      "IT'S SHOWTIME\n" +
        "TALK TO THE HAND \"C:\\\\Temp\\\\readme.txt\"\n" +
        "YOU HAVE BEEN TERMINATED\n"
    intercept[ParsingException] {
      getOutput(code)
    }
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

  it should "detect divide by zero" in {
    val code =
      "IT'S SHOWTIME\n" +
        "HEY CHRISTMAS TREE var\n" +
        "YOU SET US UP @I LIED\n" +
        "GET TO THE CHOPPER var\n" +
        "HERE IS MY INVITATION 1\n" +
        "HE HAD TO SPLIT 0\n" +
        "ENOUGH TALK\n" +
        "TALK TO THE HAND var\n" +
        "YOU HAVE BEEN TERMINATED\n"
    intercept[java.lang.ArithmeticException] {
      getOutput(code)
    }
  }
}
