package org.arnoldc

import java.io.File

object Executor {

  def execute(className:String) = {
    val classLoader = new java.net.URLClassLoader(Array(new File(".").toURI.toURL), getClass.getClassLoader)
    val clazz = classLoader.loadClass(className)
    val instance = clazz.newInstance().asInstanceOf[ {def main(test: Array[String])}]
    instance.main(null)
  }

}
