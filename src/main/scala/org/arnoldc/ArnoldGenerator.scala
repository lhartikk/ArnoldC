package org.arnoldc


class ArnoldGenerator extends ClassLoader {

  def generate(arnoldCode: String, filename: String): Array[Byte] = {
    val parser = new ArnoldParser
    val rootNode = parser.parse(arnoldCode)
    rootNode.generateByteCode(filename)
  }
}
