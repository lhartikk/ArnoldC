package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable

case class MethodNode(methodName: String, statements: List[StatementNode]) extends AbstractMethodNode {

  def generate(mv: MethodVisitor, symbolTable: SymbolTable) = {
    mv.visitCode()
    statements.foreach(_.generate(mv, symbolTable))
    mv.visitInsn(RETURN)
    mv.visitMaxs(100, 100)
    mv.visitEnd()
  }

}
