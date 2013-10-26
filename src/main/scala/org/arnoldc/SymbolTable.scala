package org.arnoldc

import scala.collection.mutable
import org.parboiled.errors.ParsingException


case class SymbolTable(upperLevel: SymbolTable) {
  private val table = new mutable.HashMap[String, VariableInformation]()
  val initialNextVarAddress: Int =
    if (upperLevel == null)
      0
    else
      upperLevel.initialNextVarAddress + 1

  def put(variableName: String, variableType: VariableType.value) = {
    val newVarAddress = initialNextVarAddress + table.size
    if (table.contains(variableName)) {
      throw new ParsingException("DUPLICATE VARIABLE: " + variableName)
    }
    table += (variableName -> VariableInformation(newVarAddress, variableType))
  }

  def get(variableName: String): VariableInformation = {
//    upperLevel.get(variableName)
    table.getOrElse(variableName, {
    //  if (upperLevel == null) {
        throw new ParsingException("VARIABLE:" + variableName + "NOT DECLARED!")
      //}
    //  return upperLevel.get(variableName)
    })
  }

  case class VariableInformation(varAddress: Int, varType: VariableType.value)

}