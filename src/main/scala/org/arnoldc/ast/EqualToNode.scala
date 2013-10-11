package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.objectweb.asm.Label

case class EqualToNode(operand1: OperandNode, operand2: OperandNode) extends ExpressionNode{
  def generate(mv: MethodVisitor) {
    operand1.generate(mv)
    operand2.generate(mv)

    val l1 = new Label()
    val l2 = new Label()
    mv.visitJumpInsn(IF_ICMPNE , l1)
    mv.visitInsn(ICONST_0)
    mv.visitLabel(l1)

    mv.visitLabel(l2)
  }
}
