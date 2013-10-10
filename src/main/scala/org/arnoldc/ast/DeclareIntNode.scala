package org.arnoldc.ast

import org.objectweb.asm.{Label, MethodVisitor}
import org.arnoldc.{VariableType, SymbolTable}
import org.objectweb.asm.Opcodes._


case class DeclareIntNode(variable: String, value: OperandNode) extends StatementNode {

  def generate(mv: MethodVisitor) = {
    SymbolTable.put(variable, VariableType.int)
    value.generate(mv)
    mv.visitVarInsn(ISTORE, SymbolTable.get(variable).varAddress)
  }

}