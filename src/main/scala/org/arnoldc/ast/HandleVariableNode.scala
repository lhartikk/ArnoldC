package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.arnoldc.SymbolTable
import org.objectweb.asm.Opcodes._

case class HandleVariableNode(variable: String ,expressions: List[ExpressionNode]) extends StatementNode {
  def generate(mv: MethodVisitor) {
    val variableAddress = SymbolTable.get(variable).varAddress
    mv.visitVarInsn(ILOAD, variableAddress)
    expressions.foreach(it => it.generate(mv))
    mv.visitVarInsn(ISTORE, variableAddress)
  }
}
