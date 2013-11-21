package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.arnoldc.SymbolTable
import org.objectweb.asm.Opcodes._
import org.objectweb.asm.Label

case class ConditionNode(condition: OperandNode, ifBranch: List[AstNode], elseBranch: List[AstNode]) extends StatementNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    val conclude = new Label()
    val falseLabel = new Label()
    condition.generate(mv, symbolTable)
    mv.visitJumpInsn(IFEQ, falseLabel)
    ifBranch.foreach(_.generate(mv, symbolTable))
    mv.visitJumpInsn(GOTO, conclude)
    mv.visitLabel(falseLabel)
    mv.visitFrame(F_FULL, symbolTable.size(), symbolTable.getStackFrame, 0, null)
    elseBranch.foreach(_.generate(mv, symbolTable))
    mv.visitJumpInsn(GOTO, conclude)
    mv.visitLabel(conclude)
    mv.visitFrame(F_SAME, 0, null, 0, null)

  }
}
