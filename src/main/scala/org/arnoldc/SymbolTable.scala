package org.arnoldc

import scala.collection.mutable
import org.parboiled.errors.ParsingException
import org.objectweb.asm.Opcodes._

case class SymbolTable(upperLevel: Option[SymbolTable], currentMethod: String) {

  val FirstSymbolTableAddress = 0
  private val variableTable = new mutable.HashMap[String, Integer]()
  private val methodTable = new mutable.HashMap[String, MethodInformation]()

  val initialNextVarAddress: Int = FirstSymbolTableAddress

  def size(): Int = {
    initialNextVarAddress + variableTable.size
  }

  def getStackFrame: Array[AnyRef] = {
    Stream.iterate(INTEGER: AnyRef) {
      i => i
    }.take(size()).toArray
  }

  def putVariable(variableName: String) = {
    val newVarAddress = initialNextVarAddress + variableTable.size
    if (variableTable.contains(variableName)) {
      throw new ParsingException("DUPLICATE VARIABLE: " + variableName)
    }
    variableTable += (variableName -> newVarAddress)
  }

  def getVariableAddress(variableName: String): Integer = {
    variableTable.getOrElse(variableName, {
      if (upperLevel.isEmpty) {
        throw new ParsingException("VARIABLE: " + variableName + " NOT DECLARED!")
      }
      upperLevel.get.getVariableAddress(variableName)
    })
  }

  def putMethod(methodName: String, methodInformation: MethodInformation) = {
    methodTable.put(methodName, methodInformation)
  }

  def getMethodDescription(methodName: String): String = {
    if (methodName.equals("main")) {
      "([Ljava/lang/String;)V"
    }
    else {
      val method = getMethodInformation(methodName)
      val numberOfArguments = method.numberOfArguments
      val returnValue = if (method.returnsValue) "I" else "V"
      "(" + "I" * numberOfArguments + ")" + returnValue
    }
  }

  def getCurrentMethod(): MethodInformation = {
    getMethodInformation(currentMethod)
  }

  def getMethodInformation(methodName: String): MethodInformation = {
    methodTable.getOrElse(methodName, {
      if (upperLevel.isEmpty) {
        throw new ParsingException("METHOD: " + methodName + " NOT DECLARED!")
      }
      upperLevel.get.getMethodInformation(methodName)
    })
  }

  def getFileName(): String = {
    if (upperLevel.isEmpty) {
      currentMethod
    }
    else {
      upperLevel.get.getFileName()
    }
  }

}