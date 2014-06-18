package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.arnoldc.SymbolTable
import org.objectweb.asm.Opcodes._

case class AssignVariableNode(variable: String, expression: AstNode) extends StatementNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    val variableAddress = symbolTable.getVariableAddress(variable)
    expression.generate(mv, symbolTable)
    mv.visitVarInsn(ISTORE, variableAddress)
  }
}
