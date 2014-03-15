package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.arnoldc.SymbolTable
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable

case class CallReadMethodNode(returnVar: String) extends StatementNode{
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) = {
    mv.visitFieldInsn(GETSTATIC, "scala/Console$", "MODULE$", "Lscala/Console$;")
    mv.visitMethodInsn(INVOKEVIRTUAL, "scala/Console$", "readInt", "()I")
    mv.visitVarInsn(ISTORE, symbolTable.getVariableAddress(returnVar))
  }
}