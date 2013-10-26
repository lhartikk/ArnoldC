package org.arnoldc

import org.parboiled.scala._
import org.parboiled.errors.{ErrorUtils, ParsingException}
import org.arnoldc.ast._

class ArnoldParser extends Parser {


  val ParseError = "WHAT THE FUCK DID I DO WRONG"

  val DeclareInt = "HEY CHRISTMAS TREE"
  val DeclareBoolean = "RIGHT? WRONG!"
  val SetInitialValue = "YOU SET US UP"
  val BeginProgram = "ITS SHOWTIME"
  val PlusOperator = "GET UP"
  val MinusOperator = "GET DOWN"
  val EndProgram = "YOU HAVE BEEN TERMINATED"
  val Print = "TALK TO THE HAND"
  val AssignVariable = "GET TO THE CHOPPER"
  val SetValue = "HERE IS MY INVITATION"
  val EndAssignVariable = "ENOUGH TALK"
  val False = "I LIED"
  val True = "NO PROBLEMO"
  val EqualTo = "YOU ARE NOT YOU YOU ARE ME"
  val GreaterThan = "GREATER"
  val Or = "HE HAD TO SPLIT"
  val And = "KNOCK KNOCK"
  val If = "BECAUSE IM GOING TO SAY PLEASE"
  val Else = "BULLSHIT"
  val EndIf = "YOU HAVE NO RESPECT FOR LOGIC"
  val While = "STICK AROUND"
  val EndWhile = "CHILL"

  val EOL = oneOrMore("\n")

  def Root: Rule1[RootNode] = rule {
    BeginProgram ~ EOL ~ zeroOrMore(Statement) ~ EndProgram ~ EOL ~ EOI ~~> RootNode
  }

  def Statement: Rule1[StatementNode] = rule {
    DeclareIntStatement | DeclareBooleanStatement | PrintStatement |
      AssignVariableStatement | ConditionStatement
  }

  def ConditionStatement: Rule1[ConditionNode] = rule {
    If ~ " " ~ Operand ~ EOL ~ zeroOrMore(Statement) ~
      (Else ~ EOL ~ zeroOrMore(Statement) ~~> ConditionNode
        | zeroOrMore(Statement) ~~> ConditionNode) ~ EndIf ~ EOL

  }

  /*def WhileStatement: Rule1[WhileNode] = rule {

  } */

  def PrintStatement: Rule1[PrintNode] = rule {
    Print ~ " " ~ (Operand ~~> PrintNode | "\"" ~ String ~ "\"" ~~> PrintNode) ~ EOL
  }

  def DeclareIntStatement: Rule1[DeclareIntNode] = rule {
    DeclareInt ~ " " ~ VariableName ~> (m => m) ~ EOL ~ SetInitialValue ~ " " ~ Operand ~~> DeclareIntNode ~ EOL
  }

  def DeclareBooleanStatement: Rule1[DeclareBooleanNode] = rule {
    DeclareBoolean ~ " " ~ VariableName ~> (m => m) ~ EOL ~ SetInitialValue ~ " " ~ Operand ~~> DeclareBooleanNode ~ EOL
  }

  def AssignVariableStatement: Rule1[AssignVariableNode] = rule {
    AssignVariable ~ " " ~ VariableName ~> (m => m) ~ EOL ~ Expression ~ EndAssignVariable ~ EOL ~~> AssignVariableNode
  }

  def Operand: Rule1[OperandNode] = rule {
    Boolean | Number | Variable
  }

  def Expression: Rule1[AstNode] = rule {
    (SetValueExpression | Boolean ~ EOL) ~
      (oneOrMore(ArithmeticOperation) | zeroOrMore(LogicalOperation))
  }

  val LogicalOperation: ReductionRule1[AstNode, AstNode] = rule {
    Or ~ EOL ~ (SetValueExpression ~ RelationalExpression | Boolean ~ EOL) ~~> OrNode |
      And ~ EOL ~ (SetValueExpression ~ RelationalExpression | Boolean ~ EOL) ~~> AndNode |
      RelationalExpression

  }

  def RelationalExpression: ReductionRule1[AstNode, AstNode] = {
    EqualToExpression ~~> EqualToNode |
      GreaterThanExpression ~~> GreaterThanNode
  }


  def EqualToExpression: Rule1[OperandNode] = {
    EqualTo ~ " " ~ Operand ~ EOL
  }

  def GreaterThanExpression: Rule1[OperandNode] = {
    GreaterThan ~ " " ~ Operand ~ EOL
  }

  def ArithmeticOperation: ReductionRule1[AstNode, AstNode] = rule {
    PlusExpression ~~> PlusExpressionNode |
      MinusExpression ~~> MinusExpressionNode
  }

  def SetValueExpression: Rule1[OperandNode] = rule {
    SetValue ~ " " ~ Operand ~ EOL
  }


  def PlusExpression: Rule1[AstNode] = rule {
    PlusOperator ~ " " ~ Operand ~ EOL
  }

  def MinusExpression: Rule1[AstNode] = rule {
    MinusOperator ~ " " ~ Operand ~ EOL
  }

  def Variable: Rule1[VariableNode] = rule {
    VariableName ~> VariableNode
  }

  def VariableName: Rule0 = rule {
    rule("A" - "Z") ~ zeroOrMore("A" - "Z" | "0" - "9")
  }

  def Boolean: Rule1[BooleanNode] = rule {
    (False | True) ~> (matchedBoolean =>
      if (matchedBoolean == True) BooleanNode(value = true)
      else BooleanNode(value = false))
  }

  def Number: Rule1[NumberNode] = rule {
    oneOrMore("0" - "9") ~> ((matched: String) => NumberNode(matched.toInt))
  }

  def String: Rule1[StringNode] = rule {
    zeroOrMore(rule {
      !anyOf("\"\\") ~ ANY
    }) ~> StringNode
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