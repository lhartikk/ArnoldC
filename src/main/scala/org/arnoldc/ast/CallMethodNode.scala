package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable

case class CallMethodNode(methodName: String, arguments: List[OperandNode]) extends StatementNode {

  def generate(mv: MethodVisitor, symbolTable: SymbolTable) = {
      arguments.foreach(_.generate(mv, symbolTable))
      //todo check method exists
      mv.visitMethodInsn(INVOKESTATIC, "Hello", methodName, "()V")
  }

}
