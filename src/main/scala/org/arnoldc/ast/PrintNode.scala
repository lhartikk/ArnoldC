package org.arnoldc.ast

import org.objectweb.asm.commons.{Method, GeneratorAdapter}
import org.objectweb.asm.{MethodVisitor, Type}
import org.objectweb.asm.Opcodes._
import java.io.PrintStream
import org.arnoldc.SymbolTable


case class PrintNode(operand: AstNode) extends StatementNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
    operand.generate(mv, symbolTable)
    if (operand.isInstanceOf[StringNode]) {
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
    }
    else {
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V")
    }
  }
}

