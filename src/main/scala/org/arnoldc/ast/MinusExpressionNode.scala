package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._

case class MinusExpressionNode(operand: OperandNode) extends ExpressionNode{
  def generate(mv: MethodVisitor) {
    operand.generate(mv)
    mv.visitInsn(ISUB)
  }
}
