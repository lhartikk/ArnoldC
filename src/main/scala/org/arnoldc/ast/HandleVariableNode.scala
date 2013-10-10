package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.arnoldc.SymbolTable
import org.objectweb.asm.Opcodes._

case class HandleVariableNode(variable: String ,expressions: List[ExpressionNode]) extends StatementNode {
  def generate(mv: MethodVisitor) {
    val variableNumber = SymbolTable.get(variable)
    mv.visitVarInsn(ILOAD, variableNumber)
    expressions.foreach(it => it.generate(mv))
    mv.visitVarInsn(ISTORE, variableNumber)
  }
}
