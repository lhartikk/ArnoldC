package org.arnoldc

import java.io.{PrintStream, ByteArrayOutputStream}

class ByteCodeExecutor extends ClassLoader {
  val originalOutputStream = System.out

  def getOutput(bytecode: Array[Byte], className: String): String = {

    val outputRedirectionStream = new ByteArrayOutputStream()

   System.setOut(new PrintStream(outputRedirectionStream))

    invokeMainMethod(bytecode, className)
    System.setOut(originalOutputStream)
    outputRedirectionStream.toString
  }

  def invokeMainMethod(bytecode: Array[Byte], className: String) = {
    val template = new ByteCodeExecutor()
    val testClass = template.defineClass(className, bytecode, 0, bytecode.length)
    val testInstance = testClass.newInstance().asInstanceOf[ {def main(test: Array[String])}]
    testInstance.main(null)
  }
}
