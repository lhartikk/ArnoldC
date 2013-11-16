package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable


case class ReturnNode(operand: Option[OperandNode]) extends StatementNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    if (operand.isEmpty) {
      mv.visitInsn(RETURN)
      mv.visitFrame(F_SAME, 0, null, 0, null);
    }
    else {
      operand.get.generate(mv, symbolTable)
      mv.visitInsn(IRETURN);
    }
  }
}

