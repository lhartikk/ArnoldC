package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._

case class MinusExpressionNode(expression: AstNode, operand: AstNode) extends ArithmeticExpressionNode {
  def generate(mv: MethodVisitor) {
    expression.generate(mv)
    operand.generate(mv)
    mv.visitInsn(ISUB)
  }
}
