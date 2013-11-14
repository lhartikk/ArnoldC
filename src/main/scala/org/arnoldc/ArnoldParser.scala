package org.arnoldc

import org.parboiled.scala._
import org.parboiled.errors.{ErrorUtils, ParsingException}
import org.arnoldc.ast._
import com.sun.org.apache.xpath.internal.operations

class ArnoldParser extends Parser {


  val ParseError = "WHAT THE FUCK DID I DO WRONG"

  val DeclareInt = "HEY CHRISTMAS TREE"
  val SetInitialValue = "YOU SET US UP"
  val BeginMain = "ITS SHOWTIME"
  val PlusOperator = "GET UP"
  val MinusOperator = "GET DOWN"
  val MultiplicationOperator = "YOU'RE FIRED"
  val DivisionOperator = "HE HAD TO SPLIT"
  val EndMain = "YOU HAVE BEEN TERMINATED"
  val Print = "TALK TO THE HAND"
  val AssignVariable = "GET TO THE CHOPPER"
  val SetValue = "HERE IS MY INVITATION"
  val EndAssignVariable = "ENOUGH TALK"
  val False = "I LIED"
  val True = "NO PROBLEMO"
  val EqualTo = "YOU ARE NOT YOU YOU ARE ME"
  val GreaterThan = "LET OF SOME STEAM BENNET"
  val Or = "CONSIDER THAT A DIVORCE"
  val And = "KNOCK KNOCK"
  val If = "BECAUSE IM GOING TO SAY PLEASE"
  val Else = "BULLSHIT"
  val EndIf = "YOU HAVE NO RESPECT FOR LOGIC"
  val While = "STICK AROUND"
  val EndWhile = "CHILL"
  val DeclareMethod = "LISTEN TO ME VERY CAREFULLY"
  val MethodArguments = "I NEED YOUR CLOTHES YOUR BOOTS AND YOUR MOTORCYCLE"
  val Return = "I'LL BE BACK"
  val EndMethodDeclaration = "HASTA LA VISTA, BABY"
  val CallMethod = "DO IT NOW"
  val NonVoidMethod = "IN TO THE TUNNEL"

  val EOL = zeroOrMore("\t" | " ") ~ "\n" ~ zeroOrMore("\t" | " " | "\n")
  val WhiteSpace = oneOrMore(" " | "\t")

  def Root: Rule1[RootNode] = rule {
    oneOrMore(AbstractMethod) ~ EOI ~~> RootNode
  }

  def AbstractMethod: Rule1[AbstractMethodNode] = rule {
    (MainMethod | Method) ~ optional(EOL)
  }

  def MainMethod: Rule1[AbstractMethodNode] = rule {
    BeginMain ~ EOL ~ zeroOrMore(Statement) ~ EndMain ~~> MainMethodNode
  }

  def Method: Rule1[AbstractMethodNode] = rule {
    DeclareMethod ~ WhiteSpace ~ VariableName ~> (s => s) ~ EOL ~
      zeroOrMore((MethodArguments ~ WhiteSpace ~ Variable ~ EOL) ) ~
      optional(NonVoidMethod) ~> (k => "int") ~
      zeroOrMore(Statement) ~ EndMethodDeclaration ~~> MethodNode


  }

  def Statement: Rule1[StatementNode] = rule {
    DeclareIntStatement | PrintStatement |
      AssignVariableStatement | ConditionStatement | WhileStatement | CallMethodStatement
  }

  def CallMethodStatement: Rule1[StatementNode] = rule {
    CallMethod ~ WhiteSpace ~ VariableName ~> (s => s) ~
      zeroOrMore(WhiteSpace ~ Operand) ~ EOL ~~> CallMethodNode
  }

  def ConditionStatement: Rule1[ConditionNode] = rule {
    If ~ " " ~ Operand ~ EOL ~ zeroOrMore(Statement) ~
      (Else ~ EOL ~ zeroOrMore(Statement) ~~> ConditionNode
        | zeroOrMore(Statement) ~~> ConditionNode) ~ EndIf ~ EOL

  }

  def WhileStatement: Rule1[WhileNode] = rule {
    While ~ " " ~ Operand ~ EOL ~ zeroOrMore(Statement) ~ EndWhile ~ EOL ~~> WhileNode
  }

  def PrintStatement: Rule1[PrintNode] = rule {
    Print ~ " " ~ (Operand ~~> PrintNode | "\"" ~ String ~ "\"" ~~> PrintNode) ~ EOL
  }

  def DeclareIntStatement: Rule1[DeclareIntNode] = rule {
    DeclareInt ~ " " ~ VariableName ~> (s => s) ~ EOL ~ SetInitialValue ~ " " ~ Operand ~~> DeclareIntNode ~ EOL
  }

  def AssignVariableStatement: Rule1[AssignVariableNode] = rule {
    AssignVariable ~ " " ~ VariableName ~> (s => s) ~ EOL ~ Expression ~ EndAssignVariable ~ EOL ~~> AssignVariableNode
  }

  def Operand: Rule1[OperandNode] = rule {
    Number | Variable | Boolean
  }

  def Expression: Rule1[AstNode] = rule {
    SetValueExpression ~
      (zeroOrMore(ArithmeticOperation | LogicalOperation))
  }

  def LogicalOperation: ReductionRule1[AstNode, AstNode] = rule {
    Or ~ WhiteSpace ~ Operand ~ EOL ~~> OrNode |
      And ~ WhiteSpace ~ Operand ~ EOL ~~> AndNode |
      EqualTo ~ WhiteSpace ~ Operand ~ EOL ~~> EqualToNode |
      GreaterThan ~ WhiteSpace ~ Operand ~ EOL ~~> GreaterThanNode

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
      MinusExpression ~~> MinusExpressionNode |
      MultiplicationExpression ~~> MultiplicationExpressionNode |
      DivisionExpression ~~> DivisionExpressionNode
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

  def MultiplicationExpression: Rule1[AstNode] = rule {
    MultiplicationOperator ~ " " ~ Operand ~ EOL
  }

  def DivisionExpression: Rule1[AstNode] = rule {
    DivisionOperator ~ " " ~ Operand ~ EOL
  }

  def Variable: Rule1[VariableNode] = rule {
    VariableName ~> VariableNode
  }

  def VariableName: Rule0 = rule {
    rule("A" - "Z" | "a" - "z") ~ zeroOrMore("A" - "Z" | "a" - "z" | "0" - "9")
  }

  def Number: Rule1[NumberNode] = rule {
    oneOrMore("0" - "9") ~> ((matched: String) => NumberNode(matched.toInt)) |
      "-" ~ oneOrMore("0" - "9") ~> ((matched: String) => NumberNode(-matched.toInt))
  }

  def Boolean: Rule1[NumberNode] = rule {
    "@" ~ True ~> (_ => NumberNode(1)) |
      "@" ~ False ~> (_ => NumberNode(0))
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