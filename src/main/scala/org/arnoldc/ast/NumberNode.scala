package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._

case class NumberNode(number: Int) extends OperandNode {
  def generate(mv: MethodVisitor) {
    mv.visitIntInsn(SIPUSH, number)
  }
}
