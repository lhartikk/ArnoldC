package org.arnoldc.ast

import org.objectweb.asm.{Label, MethodVisitor}
import org.objectweb.asm.Opcodes._
import scala.Array
import org.arnoldc.SymbolTable

case class AndNode(operand1: AstNode, operand2: AstNode) extends ExpressionNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    val eitherFalse = new Label()
    val conclude = new Label()
    operand1.generate(mv, symbolTable)
    mv.visitJumpInsn(IFEQ, eitherFalse)
    operand2.generate(mv, symbolTable)
    mv.visitJumpInsn(IFEQ, eitherFalse)
    mv.visitInsn(ICONST_1)
    mv.visitJumpInsn(GOTO, conclude)
    mv.visitLabel(eitherFalse)
    mv.visitFrame(F_FULL, symbolTable.size(), symbolTable.getStackFrame, 0, null)
    mv.visitInsn(ICONST_0)
    mv.visitJumpInsn(GOTO, conclude)
    mv.visitLabel(conclude)
    mv.visitFrame(F_SAME1, 0, null, 1, Array(INTEGER))
  }
}
