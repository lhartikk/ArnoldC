package org.arnoldc.ast

import org.objectweb.asm.{MethodVisitor, ClassWriter}
import org.objectweb.asm.Opcodes._
import org.objectweb.asm.commons.{GeneratorAdapter, Method}
import org.arnoldc.SymbolTable

abstract class AstNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable)
}
