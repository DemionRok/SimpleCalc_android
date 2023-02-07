package com.simplecalc_android

import java.util.*

class ReversePolishCalculate {
    private var stack = Stack<String>()
    private var operand = ""
    var output = listOf<String>()

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

    private fun getPriority(currOp: Char, priority: Int) {
        while (!stack.isEmpty()) {
            val opTop = stack.pop()
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
        stack.push(currOp.toString())
    }
}