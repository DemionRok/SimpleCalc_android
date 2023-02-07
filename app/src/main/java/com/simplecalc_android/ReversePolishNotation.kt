package com.simplecalc_android

import java.util.*


class ReversePolishNotation() {

    private var stack = Stack<String>()
    private var operand = ""
    private var operator = ""
    var output = listOf<String>()

    fun transform(s:String) {

        for (element in s) {
            if (element.isDigit()) {
                operand += element
                continue
            }
                when(element) {
                '+' -> getOperation(element, 1)
                '-' -> getOperation(element, 1)
                '*' -> getOperation(element, 2)
                '/' -> getOperation(element, 2)
                '(' -> stack.push(element.toString())
                ')' -> getBracket()
//                else -> {
//                    output += operand
//                    operand = ""
//                }
            }
            /*output += operand
            operand = ""*/
        }
            //output += operand
        while (!stack.isEmpty()) {
            output += stack.pop()
            if (operand.isNotEmpty()){
                output += operand
                operand = ""
            }
        }
    }

    private fun getBracket() {
        while (!stack.isEmpty()) {
            val element = stack.pop()
            if (element == "(") {
                break
            } else {
                output += element
            }
        }
    }

    private fun getOperation(currentOp: Char, priority: Int) {
        while (!stack.isEmpty()) {
            val opTop = stack.pop()
            if (opTop == "(") {
                stack.push(opTop)
                break
            } else {
                var oldPriority: Int = if (opTop == "+" || opTop == "-") 1 else 2
                if (priority > oldPriority) {
                    stack.push(opTop)
                    break
                } else {
                    output += opTop
                }
            }
        }
        stack.push(currentOp.toString())
    }
}




