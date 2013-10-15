package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.objectweb.asm.Label

case class GreaterThanNode(operand1: AstNode, operand2: AstNode) extends ExpressionNode{
  def generate(mv: MethodVisitor) {
    //TODO:
  }
}
