package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable

case class BooleanNode(value: Boolean) extends OperandNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    val opCode = if(value) ICONST_1 else ICONST_0
    mv.visitInsn(opCode)
  }
}
