package org.arnoldc

import java.io.FileOutputStream

object ArnoldC {
  def main(args: Array[String]) {
    if(args.length != 1){
      println("Usage: ArnoldC [FileToSourceCode]")
      return
    }
    val filename = args(0)
    val sourceCode = scala.io.Source.fromFile(filename).mkString

    val a = new ArnoldGenerator()
    val bytecode = a.generate(sourceCode, filename)
    val out = new FileOutputStream(filename+".class")
    out.write(bytecode)
    out.close()
  }
}