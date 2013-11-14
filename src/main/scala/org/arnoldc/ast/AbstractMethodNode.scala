package org.arnoldc.ast

abstract class AbstractMethodNode extends AstNode {
  val statements: List[StatementNode]
  val arguments: List[VariableNode]
  val methodName: String

  def signature = MethodSignature(methodName, arguments)
}

case class MethodSignature(name: String, args: List[VariableNode])