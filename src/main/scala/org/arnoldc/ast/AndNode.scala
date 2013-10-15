package org.arnoldc.ast

import org.objectweb.asm.{Label, MethodVisitor}
import org.objectweb.asm.Opcodes._
import scala.Array

case class AndNode(operand1: AstNode, operand2: AstNode) extends ExpressionNode{
  def generate(mv: MethodVisitor) {
    val eitherFalse = new Label()
    val conclude = new Label()

    operand1.generate(mv)
    mv.visitJumpInsn(IFEQ, eitherFalse)
    operand2.generate(mv)
    mv.visitJumpInsn(IFEQ, eitherFalse)
    //molemmat true
    mv.visitInsn(ICONST_1)
    mv.visitJumpInsn(GOTO, conclude)
    //jompikumpi false
    mv.visitLabel(eitherFalse)
    mv.visitFrame(F_SAME, 0, null, 0, null)
    mv.visitInsn(ICONST_0)
    mv.visitJumpInsn(GOTO, conclude)
    //lopuksi
    mv.visitLabel(conclude)
    mv.visitFrame(F_SAME1, 0, null, 1, Array(INTEGER))
  }
}
