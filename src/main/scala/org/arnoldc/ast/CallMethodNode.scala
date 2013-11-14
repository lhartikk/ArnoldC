package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable
import org.parboiled.errors.ParsingException

case class CallMethodNode(methodName: String, arguments: List[OperandNode]) extends StatementNode {

  def generate(mv: MethodVisitor, symbolTable: SymbolTable) = {
    val argumentsExpected = symbolTable.getMethodInformation(methodName).numberOfArguments
    if (arguments.size != argumentsExpected) {
      throw new ParsingException("INVALID NUMBER OF ARGUMENTS WHEN CALLING METHOD: " + methodName + "\n" +
        "EXPECTED: " + argumentsExpected + ", GOT: " + arguments.size)
    }
    arguments.foreach(_.generate(mv, symbolTable))
    mv.visitMethodInsn(INVOKESTATIC, "Hello", methodName, "(" + "Z" * arguments.size + ")V")
  }

}
