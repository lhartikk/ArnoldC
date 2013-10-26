package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.arnoldc.{VariableType, SymbolTable}
import org.objectweb.asm.Opcodes._
import org.parboiled.errors.ParsingException


case class DeclareBooleanNode(variable: String, value: OperandNode) extends StatementNode {

  def generate(mv: MethodVisitor, symbolTable: SymbolTable) = {
    symbolTable.put(variable, VariableType.boolean)
    value.generate(mv, symbolTable)
    if (value.isInstanceOf[BooleanNode] || value.isInstanceOf[VariableNode]) {
      mv.visitVarInsn(ISTORE, symbolTable.get(variable).varAddress)
    }
    else throw new ParsingException("CANNOT INITIALIZE BOOLEAN WITH INT VALUE")
  }

}