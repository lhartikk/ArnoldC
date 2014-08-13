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
      "I'M GOING TO ASK YOU A BUNCH OF QUESTIONS AND I WANT TO HAVE THEM ANSWERED IMMEDIATELY\n" +
      "TALK TO THE HAND result\n" +
      "TALK TO THE HAND \"Bye\"\n" +
      "YOU HAVE BEEN TERMINATED\n"
    getOutput(code) should equal("Input a number:\n123\nBye\n")
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
