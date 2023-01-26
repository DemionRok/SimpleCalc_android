package com.simplecalc_android

import java.util.*

class Calculate {
    private var operand = ""
    private var input = listOf<String>()
    private var stack = Stack<Float>()
    private var result = 0f

    fun doCalc(v: String): Float {
        var rpn = ReversePolishNotation()
        rpn.transform(v)
        input = rpn.output

        for (element in input) {
            var ch = element
            if (ch in "0".."9") {
                operand += ch
                stack.push(operand.toFloat())
                operand = ""
            } else {
                var num1 = stack.pop()
                var num2 = stack.pop()
                var result = when (ch) {
                    "+" -> num2 + num1
                    "-" -> num2 - num1
                    "*" -> num2 * num1
                    "/" -> num2 / num1
                    else -> 0
                }
                stack.push(result.toFloat())
            }
        }
       result = stack.pop()
        return result
    }
}




