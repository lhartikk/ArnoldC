package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.arnoldc.{VariableType, SymbolTable}
import org.objectweb.asm.Opcodes._


case class DeclareBooleanNode(variable: String, value: OperandNode) extends StatementNode {

  def generate(mv: MethodVisitor) = {
    SymbolTable.put(variable, VariableType.boolean)
    value.generate(mv)
    //TODO: CHECK VARIABLE IS BOOLEAN
    mv.visitVarInsn(ISTORE, SymbolTable.get(variable).varAddress)
  }

}