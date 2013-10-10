package org.arnoldc

import scala.collection.mutable
import org.parboiled.errors.ParsingException

object SymbolTable {
  private val table = new mutable.HashMap[String, Int]()
  var counter = 0

  def clear(){
    table.clear()
  }

  def put(variableName: String) = {
    counter = counter + 1
    if (table.contains(variableName)){
      throw new ParsingException("DUPLICATE VARIABLE: " + variableName)
    }
    table += (variableName -> counter)
  }

  def get(variableName: String): Int = {
    table.getOrElse(variableName, {
      throw new ParsingException("VARIABLE:" + variableName + "NOT DECLARED!")
    })
  }
}
