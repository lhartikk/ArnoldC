package org.arnoldc.ast

import org.objectweb.asm.Opcodes._
import org.objectweb.asm.{MethodVisitor, ClassWriter}
import org.arnoldc.SymbolTable

case class RootNode(statements: List[StatementNode]) extends AstNode {

  val cw = new ClassWriter(0)
  val symbolTable = new SymbolTable(None)

  def generateByteCode(filename: String): Array[Byte] = {
    generateClass(filename)
    cw.toByteArray
  }

  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    val mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC,
      "main",
      "([Ljava/lang/String;)V",
      null,
      null)
    statements.foreach(it => it.generate(mv, symbolTable))
    mv.visitInsn(RETURN)
    mv.visitMaxs(100, 100)
    mv.visitEnd()
  }

  def generateClass(className: String) = {
    cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, className, null, "java/lang/Object", null)
    cw.visitSource("Hello.java", null)
    val mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null)
    mv.visitVarInsn(ALOAD, 0)
    mv.visitMethodInsn(INVOKESPECIAL,
      "java/lang/Object",
      "<init>",
      "()V")
    mv.visitInsn(RETURN)
    mv.visitMaxs(100, 100)
    mv.visitEnd()
    generate(mv, symbolTable)
    cw.visitEnd()
  }


}
