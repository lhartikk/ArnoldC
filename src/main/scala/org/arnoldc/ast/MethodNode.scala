package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable

case class MethodNode(methodName: String, arguments: List[VariableNode], s: String, statements: List[StatementNode]) extends AbstractMethodNode {

  def generate(mv: MethodVisitor, symbolTable: SymbolTable) = {
    val methodSymbolTable = new SymbolTable(None)

    mv.visitCode()
    arguments.foreach {
      a =>
        methodSymbolTable.putVariable(a.variableName)
    }
    statements.foreach(_.generate(mv, methodSymbolTable))
    mv.visitInsn(RETURN)
    mv.visitMaxs(100, 100)
    mv.visitEnd()
  }

}
