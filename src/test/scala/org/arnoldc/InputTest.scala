package org.arnoldc
import org.scalatest.{Matchers, FlatSpec}
import java.io._

class InputTest extends ArnoldGeneratorTest{

  it should "read integer from input" in {
    writeToFile(path, "123")
    val code =
      "IT'S SHOWTIME\n" +
      "TALK TO THE HAND \"Input a number:\"\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "TALK TO THE HAND \"Bye\"\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("Input a number:\n123\nBye\n")
  }

  it should "read max short from input" in {
    writeToFile(path, "32767")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("32767\n")
  }
  it should "read max int from input" in {
    writeToFile(path, "2147483647")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("2147483647\n")
  }
  it should "read min short from input" in {
    writeToFile(path, "-32768")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-32768\n")
  }
  it should "read min int from input" in {
    writeToFile(path, "-2147483648")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("-2147483648\n")
  }
  it should "read leading zero from input" in {
    writeToFile(path, "0200")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("200\n")
  }
  it should "read leading plus sign and zero from input" in {
    writeToFile(path, "+0200")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("200\n")
  }
  it should "read number with leading and trailing whitespace from input" in {
    writeToFile(path, " 2 ")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("2\n")
  }
  it should "read number, space, number from input" in {
    writeToFile(path, "2 2")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("2\n")
  }
  it should "read number, space, alpha from input" in {
    writeToFile(path, "2 a")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("2\n")
  }

  it should "detect alpha from input" in {
    writeToFile(path, "a")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    intercept[java.util.InputMismatchException] {
      getOutput(code)
    }
  }
  it should "detect alpha numeric from input" in {
    writeToFile(path, "1a")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    intercept[java.util.InputMismatchException] {
      getOutput(code)
    }
  }
  it should "detect float from input" in {
    writeToFile(path, "1.0")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    intercept[java.util.InputMismatchException] {
      getOutput(code)
    }
  }
  it should "detect hex from input" in {
    writeToFile(path, "0x2")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    intercept[java.util.InputMismatchException] {
      getOutput(code)
    }
  }
  it should "detect scientific notation from input" in {
    writeToFile(path, "3.30e2")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    intercept[java.util.InputMismatchException] {
      getOutput(code)
    }
  }
  it should "detect int overflow from input" in {
    writeToFile(path, "2147483648")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    intercept[java.util.InputMismatchException] {
      getOutput(code)
    }
  }
  it should "detect int underflow from input" in {
    writeToFile(path, "-2147483649")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    intercept[java.util.InputMismatchException] {
      getOutput(code)
    }
  }
  it should "detect only minus sign from input" in {
    writeToFile(path, "-")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    intercept[java.util.InputMismatchException] {
      getOutput(code)
    }
  }
  it should "detect minus sign, space, number from input" in {
    writeToFile(path, "- 2")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    intercept[java.util.InputMismatchException] {
      getOutput(code)
    }
  }
  it should "detect only plus sign from input" in {
    writeToFile(path, "+")
    val code =
      "IT'S SHOWTIME\n" +
      "HEY CHRISTMAS TREE result\n" +
      "YOU SET US UP 0\n" +
      "GET YOUR ASS TO MARS result\n" +
      "DO IT NOW\n" +
      "I WANT TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "YOU HAVE BEEN TERMINATED\n"
    intercept[java.util.InputMismatchException] {
      getOutput(code)
    }
  }

  val path = "test.in"

  override val byteCodeExecutor = new ByteCodeExecutor{

    override def getOutput(bytecode: Array[Byte], className: String): String = {

      val originalIn = System.in
      val outputRedirectionStream = new ByteArrayOutputStream()
      val fileInput = new BufferedInputStream(new FileInputStream(path))
      System.setOut(new PrintStream(outputRedirectionStream))
      System.setIn(fileInput)

      try {
        invokeMainMethod(bytecode, className)
      } finally {
        System.setOut(originalOutputStream)
        System.setIn(originalIn)
      }
      outputRedirectionStream.toString
    }
  }

  def writeToFile(file:String, data:String) = {
    val out = new FileOutputStream(file)
    out.write(data.getBytes)
    out.close()
  }


}