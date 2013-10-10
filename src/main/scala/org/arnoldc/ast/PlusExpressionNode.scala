package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._

case class PlusExpressionNode(operand: OperandNode) extends ExpressionNode{
  def generate(mv: MethodVisitor) {
    operand.generate(mv)
    mv.visitInsn(IADD)
  }
}
