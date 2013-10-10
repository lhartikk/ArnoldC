package org.arnoldc.ast

import org.objectweb.asm.commons.{Method, GeneratorAdapter}
import org.objectweb.asm.{MethodVisitor, Type}
import org.objectweb.asm.Opcodes._
import java.io.PrintStream


case class PrintNode(operand: OperandNode) extends StatementNode {
  def generate(mv: MethodVisitor) {
    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
    operand.generate(mv)
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V")
  }
}

