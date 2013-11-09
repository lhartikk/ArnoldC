package org.arnoldc

case class VariableInformation(varAddress: Int, varType: VariableType.value)

case class MethodInformation(returnValue: VariableType.value, arguments: List[VariableType.value])