package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.arnoldc.SymbolTable
import org.parboiled.errors.ParsingException

case class CallMethodNode(returnVar: String, methodName: String, arguments: List[OperandNode]) extends StatementNode {

  def generate(mv: MethodVisitor, symbolTable: SymbolTable) = {
    val argumentsExpected = symbolTable.getMethodInformation(methodName).numberOfArguments
    if (arguments.size != argumentsExpected) {
      throw new ParsingException("INVALID NUMBER OF ARGUMENTS WHEN CALLING METHOD: " + methodName + "\n" +
        "EXPECTED: " + argumentsExpected + ", GOT: " + arguments.size)
    }
    arguments.foreach(_.generate(mv, symbolTable))
    mv.visitMethodInsn(INVOKESTATIC, symbolTable.getFileName(), methodName, symbolTable.getMethodDescription(methodName))
    handleStackAfterCall

    def handleStackAfterCall {
      if (returnVar != "") {
        if (!symbolTable.getMethodInformation(methodName).returnsValue) {
          throw new ParsingException("CANNOT ASSIGN VALUE TO VARIABLE " + returnVar + ", METHOD " + methodName + " IS A TYPE OF VOID")
        }
        mv.visitVarInsn(ISTORE, symbolTable.getVariableAddress(returnVar))
      }
      else if (symbolTable.getMethodInformation(methodName).returnsValue) {
        mv.visitInsn(POP)
      }
    }
  }
}
