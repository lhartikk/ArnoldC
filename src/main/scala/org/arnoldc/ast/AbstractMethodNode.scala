package org.arnoldc.ast

abstract class AbstractMethodNode() extends AstNode {
  val statements: List[StatementNode]
  val methodName: String
}