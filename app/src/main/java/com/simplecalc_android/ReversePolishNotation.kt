package com.simplecalc_android

import java.util.*

class ReversePolishNotation {
    private var stack = Stack<String>()
    private var operand = ""
    var output: Array<String> = arrayOf()

    fun doReverse(s: String) {
        for (element in s) {
            if (element.isDigit() || element == '.') {
                operand += element
                continue
            } else {
                when (element) {
                    '+' -> getPriority(element, 1)
                    '*' -> getPriority(element, 2)
                    '-' -> getPriority(element, 1)
                    '/' -> getPriority(element, 2)
                    '(' -> stack.push(element.toString())
                    ')' -> getBracket()
                }
            }
            if (operand.isNotEmpty()) {
                output += operand
                operand = ""
            }
        }
        while (!stack.isEmpty()) {
            if (operand.isNotEmpty()) {
                output += operand
                operand = ""
            }
            output += stack.pop()
        }
    }

    private fun getBracket() {
        while (!stack.isEmpty()) {
            val element = stack.pop()
            if (element == "(") {
                break
            } else {
                if (operand.isNotEmpty()) {
                    output += operand
                    operand = ""
                }
                output += element
            }
        }
    }

    private fun getPriority(currOp: Char, priority: Int) {
        while (!stack.isEmpty()) {
            val opTop = stack.pop()
            if (opTop == "(") {
                stack.push(opTop)
                break
            } else {
                var oldPriority = if (opTop == "+" || opTop == "-") 1 else 2
                if (priority > oldPriority) {
                    stack.push(opTop)
                    break
                } else {
                    if (operand.isNotEmpty()) {
                        output += operand
                        operand = ""
                    }
                    output += opTop.toString()
                }
            }
        }
        stack.push(currOp.toString())
    }
}