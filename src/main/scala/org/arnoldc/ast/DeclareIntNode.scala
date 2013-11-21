package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.arnoldc.{SymbolTable}
import org.objectweb.asm.Opcodes._
import org.parboiled.errors.ParsingException


case class DeclareIntNode(variable: String, value: OperandNode) extends StatementNode {

  def generate(mv: MethodVisitor, symbolTable: SymbolTable) = {
    symbolTable.putVariable(variable)
    value.generate(mv, symbolTable)
    if (value.isInstanceOf[NumberNode] || value.isInstanceOf[VariableNode]) {
      mv.visitVarInsn(ISTORE, symbolTable.getVariableAddress(variable))
    }
    else throw new ParsingException("CANNOT INITIALIZE INT WITH BOOLEAN VALUE")
  }

}