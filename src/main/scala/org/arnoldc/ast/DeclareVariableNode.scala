package org.arnoldc.ast

import org.objectweb.asm.{Label, MethodVisitor}
import org.arnoldc.SymbolTable
import org.objectweb.asm.Opcodes._


case class DeclareVariableNode(variable: String, value: OperandNode) extends StatementNode {

  def generate(mv: MethodVisitor) = {
    SymbolTable.put(variable)
    value.generate(mv)
    mv.visitVarInsn(ISTORE, SymbolTable.get(variable))
  }

}