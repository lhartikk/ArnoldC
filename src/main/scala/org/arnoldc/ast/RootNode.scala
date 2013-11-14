package org.arnoldc.ast

import org.objectweb.asm.Opcodes._
import org.objectweb.asm.{MethodVisitor, ClassWriter}
import org.arnoldc.{MethodInformation, SymbolTable}

case class RootNode(methods: List[AbstractMethodNode]) extends AstNode {

  val cw = new ClassWriter(0)
  val symbolTable = new SymbolTable(None)

  def generateByteCode(filename: String): Array[Byte] = {
    generateClass(filename)
    cw.toByteArray
  }

  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    storeMethodSignatures(symbolTable)
    generateMethods(symbolTable)
  }

  def generateMethods(symbolTable: SymbolTable) {
    methods.foreach {
      it =>
        it.generate(cw.visitMethod(
          ACC_PUBLIC + ACC_STATIC, it.methodName,
          symbolTable.getMethodDescription(it.methodName), null, null),
          symbolTable)
    }
  }

  def storeMethodSignatures(symbolTable: SymbolTable) {
    methods.foreach(
      it => symbolTable.putMethod(it.methodName, new MethodInformation(false, it.arguments.size))
    )
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
