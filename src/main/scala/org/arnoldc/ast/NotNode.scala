package org.arnoldc.ast

import org.objectweb.asm.{Opcodes, MethodVisitor, Label}
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable


case class  NotNode(expression: AstNode) extends ExpressionNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {

    val expressionWasTrue = new Label()
    val conclude = new Label()
    expression.generate(mv, symbolTable)
    mv.visitJumpInsn(IFNE, expressionWasTrue)
    mv.visitInsn(ICONST_1)
    mv.visitJumpInsn(GOTO, conclude)

    mv.visitLabel(expressionWasTrue)
    mv.visitFrame(F_FULL, symbolTable.size(), symbolTable.getStackFrame, 0, null)
    mv.visitInsn(ICONST_0)

    mv.visitLabel(conclude)
    mv.visitFrame(F_SAME1, 0, null, 1, Array(INTEGER))
  }
}
