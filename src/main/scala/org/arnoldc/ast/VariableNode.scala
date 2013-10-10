package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable

case class VariableNode(variable: String) extends OperandNode{
  def generate(mv: MethodVisitor) {
    mv.visitVarInsn(ILOAD, SymbolTable.get(variable))
  }
}
