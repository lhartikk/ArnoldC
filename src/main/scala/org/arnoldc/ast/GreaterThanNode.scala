package org.arnoldc.ast

import org.objectweb.asm.{Opcodes, MethodVisitor, Label}
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable

case class GreaterThanNode(operand1: AstNode, operand2: AstNode) extends ExpressionNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {

    val greaterThanLabel = new Label
    val concludeLabel = new Label
    operand1.generate(mv, symbolTable)
    operand2.generate(mv, symbolTable)
    mv.visitJumpInsn(IF_ICMPGT, greaterThanLabel)
    mv.visitInsn(ICONST_0)
    mv.visitJumpInsn(GOTO, concludeLabel)
    mv.visitLabel(greaterThanLabel)
    mv.visitFrame(F_FULL, symbolTable.size(), symbolTable.getStackFrame, 0, null)
    mv.visitInsn(ICONST_1)
    mv.visitJumpInsn(GOTO, concludeLabel)
    mv.visitLabel(concludeLabel)
    mv.visitFrame(F_SAME1, 0, null, 1, Array(INTEGER))
  }
}
