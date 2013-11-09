package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.arnoldc.{VariableType, SymbolTable}
import org.objectweb.asm.Opcodes._
import org.parboiled.errors.ParsingException


case class DeclareIntNode(variable: String, value: OperandNode) extends StatementNode {

  def generate(mv: MethodVisitor, symbolTable: SymbolTable) = {
    symbolTable.putVariable(variable, VariableType.int)
    value.generate(mv, symbolTable)
    if (value.isInstanceOf[NumberNode] || value.isInstanceOf[VariableNode]) {
      mv.visitVarInsn(ISTORE, symbolTable.getVariable(variable).varAddress)
    }
    else throw new ParsingException("CANNOT INITIALIZE INT WITH BOOLEAN VALUE")
  }

}