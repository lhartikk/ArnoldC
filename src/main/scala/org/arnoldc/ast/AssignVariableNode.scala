package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.arnoldc.SymbolTable
import org.objectweb.asm.Opcodes._

case class AssignVariableNode(variable: String ,expression: AstNode) extends StatementNode {
  def generate(mv: MethodVisitor) {
    val variableAddress = SymbolTable.get(variable).varAddress
    expression.generate(mv)
    mv.visitVarInsn(ISTORE, variableAddress)
  }
}
