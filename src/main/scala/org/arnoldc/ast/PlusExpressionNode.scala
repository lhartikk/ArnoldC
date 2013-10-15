package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._

case class PlusExpressionNode(expression: AstNode ,operand: AstNode ) extends AstNode{
  def generate(mv: MethodVisitor) {
    expression.generate(mv)
    operand.generate(mv)
    mv.visitInsn(IADD)
  }
}
