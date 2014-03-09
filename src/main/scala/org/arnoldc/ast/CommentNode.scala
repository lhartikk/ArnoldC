package org.arnoldc.ast

import org.objectweb.asm.MethodVisitor
import org.arnoldc.SymbolTable

case class CommentNode(text: String) extends StatementNode{
  override def generate(mv: MethodVisitor, symbolTable: SymbolTable){}
}
