package org.arnoldc

import java.io.FileOutputStream

object ArnoldC {
  def main(args: Array[String]) {
    if (args.length < 1) {
      println("Usage: ArnoldC [-run] [FileToSourceCode]")
      return
    }
    val filename = getFilNameFromArgs(args)
    val sourceCode = scala.io.Source.fromFile(filename).mkString
    val a = new ArnoldGenerator()
    val classFilename = if (filename.contains('.')) {
      filename.replaceAll("\\.[^.]*$", "")
    }
    else {
      filename
    }
    val bytecode = a.generate(sourceCode, classFilename)

    val out = new FileOutputStream(classFilename + ".class")
    out.write(bytecode)
    out.close()

    processOption(getCommandFromArgs(args), classFilename)

  }
  
  def getFilNameFromArgs(args:Array[String]):String = args.length match {
    case 1 => args(0)
    case 2 => args(1)
    case _ => throw new RuntimeException("What the fuck did I do wrong!")
  }

  def getCommandFromArgs(args:Array[String]):String = args.length match {
    case 2 => args(0)
    case 1 => ""
    case _ => throw new RuntimeException("What the fuck did I do wrong!")
  }

  def processOption(command:String, argFunc: => String):Unit = command match {
    case "-run" => Executor.execute(argFunc)
    case _ =>
  }

}