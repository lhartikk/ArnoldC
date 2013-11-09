package org.arnoldc

import scala.collection.mutable
import org.parboiled.errors.ParsingException
import org.objectweb.asm.Opcodes._

case class SymbolTable(upperLevel: Option[SymbolTable]) {

  val FirstSymbolTableAddress = 0
  private val variableTable = new mutable.HashMap[String, VariableInformation]()
  private val methodTable = new mutable.HashMap[String, MethodInformation]()
  
  val initialNextVarAddress: Int =
    if (upperLevel.isEmpty) {
      FirstSymbolTableAddress
    }
    else {
      upperLevel.get.initialNextVarAddress + 1
    }

  def size(): Int = {
    initialNextVarAddress + variableTable.size
  }

  def getStackFrame: Array[AnyRef] = {
    Stream.iterate(INTEGER: AnyRef) {
      i => i
    }.take(size()).toArray
  }

  def putVariable(variableName: String, variableType: VariableType.value) = {
    val newVarAddress = initialNextVarAddress + variableTable.size
    if (variableTable.contains(variableName)) {
      throw new ParsingException("DUPLICATE VARIABLE: " + variableName)
    }
    variableTable += (variableName -> VariableInformation(newVarAddress, variableType))
  }

  def getVariable(variableName: String): VariableInformation = {
    variableTable.getOrElse(variableName, {
      if (upperLevel.isEmpty) {
        throw new ParsingException("VARIABLE: " + variableName + "NOT DECLARED!")
      }
      upperLevel.get.getVariable(variableName)
    })
  }

  def putMethod(methodName: String, methodInformation: MethodInformation) = {
         methodTable.put(methodName, methodInformation)
  }

  def getMethodDescription(methodName: String): String = {
    if(methodName.equals("main")){
      "([Ljava/lang/String;)V"
    }
    else{
      "()V"
    }
  }

}