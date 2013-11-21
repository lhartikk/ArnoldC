package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable

case class MethodNode(methodName: String, arguments: List[VariableNode], returnsValue: Boolean, statements: List[StatementNode]) extends AbstractMethodNode {

  def generate(mv: MethodVisitor, symbolTable: SymbolTable) = {
    val methodSymbolTable = new SymbolTable(Some(symbolTable), methodName)
    mv.visitCode()
    arguments.foreach {
      a =>
        methodSymbolTable.putVariable(a.variableName)
    }
    statements.foreach(_.generate(mv, methodSymbolTable))
    if (!returnsValue) {
      mv.visitInsn(RETURN)
    }
    else{
      mv.visitInsn(ICONST_1)
      mv.visitInsn(IRETURN)
    }
    mv.visitMaxs(100, 100)
    mv.visitEnd()
  }

}
