package org.arnoldc

import scala.collection.mutable
import org.parboiled.errors.ParsingException

object SymbolTable {
  private val table = new mutable.HashMap[String, VariableInformation]()
  var nextVarAddress = 0

  def clear(){
    table.clear()
  }

  def put(variableName: String, variableType: VariableType.value) = {
    nextVarAddress = nextVarAddress + 1
    if (table.contains(variableName)){
      throw new ParsingException("DUPLICATE VARIABLE: " + variableName)
    }
    table += (variableName ->  VariableInformation(nextVarAddress, variableType))
  }

  def get(variableName: String): VariableInformation = {
    table.getOrElse(variableName, {
      throw new ParsingException("VARIABLE:" + variableName + "NOT DECLARED!")
    })
  }
  
  case class VariableInformation(varAddress: Int, varType: VariableType.value)
}
