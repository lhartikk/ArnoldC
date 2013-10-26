package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.objectweb.asm.Label
import org.arnoldc.SymbolTable


case class OrNode(operand1: AstNode, operand2: AstNode) extends ExpressionNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {

    val eitherTrue = new Label()
    val conclude = new Label()

  //  operand1.generate(mv, symbolTable)
    /*  mv.visitJumpInsn(IFEQ, eitherTrue)
     operand2.generate(mv, symbolTable)
      mv.visitJumpInsn(IFNE, eitherTrue)
      //molemmat false
      mv.visitInsn(ICONST_0)
   */
        mv.visitJumpInsn(GOTO, conclude)
      //jompikumpi true
  /*
      mv.visitLabel(eitherTrue)
      mv.visitFrame(F_SAME, 0, null, 0, null)
      mv.visitInsn(ICONST_1)
      mv.visitJumpInsn(GOTO, conclude)
      //lopuksi
    */
      mv.visitLabel(conclude)
      mv.visitFrame(F_SAME, 0, null, 0, null)
    mv.visitInsn(ICONST_1)
   //   mv.visitFrame(F_SAME1, 0, null, 1, Array(INTEGER))



  }
}
