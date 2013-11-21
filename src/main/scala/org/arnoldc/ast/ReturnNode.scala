package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable
import org.parboiled.errors.ParsingException


case class ReturnNode(operand: Option[OperandNode]) extends StatementNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    if (operand.isEmpty) {
      if (symbolTable.getCurrentMethod().returnsValue) {
        throw new ParsingException("NON VOID METHOD: " + symbolTable.currentMethod + " MUST RETURN AN ARGUMENT")
      }
      mv.visitInsn(RETURN)
      mv.visitFrame(F_SAME, 0, null, 0, null)
    }
    else {
      if (!symbolTable.getCurrentMethod().returnsValue) {
        throw new ParsingException("VOID METHOD: " + symbolTable.currentMethod + " CANNOT RETURN AN ARGUMENT")
      }
      operand.get.generate(mv, symbolTable)
      mv.visitInsn(IRETURN)
      mv.visitFrame(F_SAME, 0, null, 0, null)

    }
  }
}

