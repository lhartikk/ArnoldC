package org.arnoldc

import scala.collection.mutable
import org.parboiled.errors.ParsingException
import org.objectweb.asm.Opcodes._

case class SymbolTable(upperLevel: Option[SymbolTable]) {

  val FirstSymbolTableAddress = 0
  private val table = new mutable.HashMap[String, VariableInformation]()
  val initialNextVarAddress: Int =
    if (upperLevel.isEmpty) {
      FirstSymbolTableAddress
    }
    else {
      upperLevel.get.initialNextVarAddress + 1
    }

  def size(): Int = {
    initialNextVarAddress + table.size
  }

  def stackFrame(): Array[AnyRef] = {
    Stream.iterate(INTEGER: AnyRef) {
      i => i
    }.take(size()).toArray
  }

  def put(variableName: String, variableType: VariableType.value) = {
    val newVarAddress = initialNextVarAddress + table.size
    if (table.contains(variableName)) {
      throw new ParsingException("DUPLICATE VARIABLE: " + variableName)
    }
    table += (variableName -> VariableInformation(newVarAddress, variableType))
  }

  def get(variableName: String): VariableInformation = {
    table.getOrElse(variableName, {
      if (upperLevel.isEmpty) {
        throw new ParsingException("VARIABLE: " + variableName + "NOT DECLARED!")
      }
      upperLevel.get.get(variableName)
    })
  }

}