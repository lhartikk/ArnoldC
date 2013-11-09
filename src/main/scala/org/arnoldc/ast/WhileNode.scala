package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.objectweb.asm.Label
import org.arnoldc.SymbolTable

case class WhileNode(condition: OperandNode, statements: List[AstNode]) extends StatementNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    val loopStart = new Label()
    val loopEnd = new Label()

    mv.visitLabel(loopStart)
    mv.visitFrame(F_FULL, symbolTable.size(), symbolTable.getStackFrame, 0, null)
    condition.generate(mv, symbolTable)
    mv.visitJumpInsn(IFEQ, loopEnd)
    statements.foreach(_.generate(mv, symbolTable))
    mv.visitJumpInsn(GOTO, loopStart)
    mv.visitLabel(loopEnd)
    mv.visitFrame(F_SAME, 0, null, 0, null)
  }
}
