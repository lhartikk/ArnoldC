package org.arnoldc

import org.parboiled.scala._
import org.parboiled.errors.{ErrorUtils, ParsingException}
import org.arnoldc.ast._

class ArnoldParser extends Parser {


  val ParseError = "WHAT THE FUCK DID I DO WRONG"

  val DeclareInt = "HEY CHRISTMAS TREE"
  val SetInitialValue = "YOU SET US UP"
  val BeginProgram = "ITS SHOWTIME"
  val PlusOperator = "GET UP"
  val MinusOperator = "GET DOWN"
  val EndProgram = "YOU HAVE BEEN TERMINATED"
  val Print = "TALK TO THE HAND"
  val HandleVariable = "GET TO THE CHOPPER"
  val EndHandleVariable = "ENOUGH TALK"

  val EOL = oneOrMore("\n")

  def Root: Rule1[RootNode] = rule {
    BeginProgram ~ EOL ~ zeroOrMore(Statement) ~ EndProgram ~ EOL ~ EOI ~~> RootNode
  }

  def Statement: Rule1[StatementNode] = rule {
    DeclareVariableStatement | PrintStatement | HandleVariableStatement
  }

  def PrintStatement: Rule1[PrintNode] = rule {
    Print ~ " " ~ Operand ~~> PrintNode ~ EOL
  }

  def DeclareVariableStatement: Rule1[DeclareVariableNode] = rule {
    DeclareInt ~ " " ~ VariableName ~> (m=>m) ~ EOL ~ SetInitialValue ~ " " ~ Operand ~~> DeclareVariableNode ~ EOL
  }

  def HandleVariableStatement: Rule1[HandleVariableNode] = rule {
    HandleVariable ~ " " ~ VariableName ~> (m=>m) ~ EOL ~ oneOrMore(Expression) ~~> HandleVariableNode ~ EndHandleVariable ~ EOL
  }

  def Operand: Rule1[OperandNode] = rule {
    Number | Variable
  }

  def Expression: Rule1[ExpressionNode] = rule {
    (PlusExpression | MinusExpression) ~ EOL
  }

  def PlusExpression: Rule1[PlusExpressionNode] = rule {
    PlusOperator ~ " " ~ Operand ~~> PlusExpressionNode
  }

  def MinusExpression: Rule1[MinusExpressionNode] = rule {
    MinusOperator ~ " " ~ Operand ~~> MinusExpressionNode
  }

  def Variable: Rule1[VariableNode] = rule {
    VariableName ~> VariableNode
  }

  def VariableName: Rule0 = rule {
    rule("A" - "Z") ~ zeroOrMore("A" - "Z" | "0" - "9")
  }

  def Number: Rule1[NumberNode] = rule {
    oneOrMore("0" - "9") ~> ((matched: String) => NumberNode(matched.toInt))
  }

  def parse(expression: String): RootNode = {
    val parsingResult = ReportingParseRunner(Root).run(expression)
    parsingResult.result match {
      case Some(root) => root
      case None => throw new ParsingException(ParseError + ":\n" +
        ErrorUtils.printParseErrors(parsingResult))
    }
  }

}