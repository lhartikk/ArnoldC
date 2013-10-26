package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable

case class PlusExpressionNode(expression: AstNode ,operand: AstNode ) extends AstNode{
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    expression.generate(mv, symbolTable)
    operand.generate(mv, symbolTable)
    mv.visitInsn(IADD)
  }
}
