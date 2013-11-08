package org.arnoldc.ast

import org.objectweb.asm.{Opcodes, MethodVisitor, Label}
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable


case class OrNode(operand1: AstNode, operand2: AstNode) extends ExpressionNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {

    val eitherTrue = new Label()
    val conclude = new Label()
    operand1.generate(mv, symbolTable)
    mv.visitJumpInsn(IFNE, eitherTrue)
    operand2.generate(mv, symbolTable)
    mv.visitJumpInsn(IFNE, eitherTrue)

    //both false
    mv.visitInsn(ICONST_0)
    mv.visitJumpInsn(GOTO, conclude)

    //either true
    mv.visitLabel(eitherTrue)
    mv.visitFrame(Opcodes.F_FULL, symbolTable.size(), symbolTable.stackFrame(), 0, null)
    mv.visitInsn(ICONST_1)
    mv.visitJumpInsn(GOTO, conclude)
    mv.visitLabel(conclude)
    mv.visitFrame(Opcodes.F_FULL, 0, null, 1, Array(INTEGER))
  }
}
