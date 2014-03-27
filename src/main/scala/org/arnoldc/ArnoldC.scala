package org.arnoldc

import java.io.FileOutputStream

object ArnoldC {
  val Version = "0.1"

  val Usage = "Usage: ArnoldC -version\n" +
    "or ArnoldC FileToSourceCode\n" +
    "\t(to compile source)\n" +
    "or ArnoldC -run FileToSourceCode\n" +
    "\t(to compile and run)"

  def main(args: Array[String]) {
    processArguments(args.toList)
  }

  def processArguments(args:List[String]) = args match {
    case Nil => printUsage()
    case "-version" :: Nil => printCompilerVersion()
    case "-run" :: arg :: Nil => compileAndExecute(arg)
    case arg::Nil => compile(arg)
  }

  def printUsage(){
    println(Usage)
  }
  
  def printCompilerVersion(){
    println(s"ArnoldC version $Version")
  }
  
  def compile(filename:String):String ={
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
    classFilename
  }

  def execute(filename:String){
    Executor.execute(filename)
  }

  def compileAndExecute(filename:String){
    execute(compile(filename))
  }

}
