package org.arnoldc

import java.io.FileOutputStream

object ArnoldC {
  def main(args: Array[String]) {
    if (args.length != 1) {
      println("Usage: ArnoldC [FileToSourceCode]")
      return
    }
    val filename = args(0)
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
  }

}