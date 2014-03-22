package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable

case class CallReadMethodNode(returnVar: String) extends StatementNode{
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) = {
    mv.visitTypeInsn(NEW, "java/util/Scanner");
    mv.visitInsn(DUP);
    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;")
    mv.visitMethodInsn(INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I")
    mv.visitVarInsn(ISTORE, symbolTable.getVariableAddress(returnVar))
  }
}
