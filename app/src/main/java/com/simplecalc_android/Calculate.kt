package com.simplecalc_android

import androidx.core.text.isDigitsOnly
import java.util.*

class Calculate {
    private var operand = ""
    private var input:Array<String> = arrayOf()
    private var stack = Stack<Float>()
    private var result = 0f

    fun doCalc(v: String): Float {
        var rpn = ReversePolishNotation()
        rpn.doReverse(v)
        input = rpn.output
        for (element in input) {
            if (element.isDigitsOnly()) {
                stack.push(element.toFloat())
            } else {
                var num1 = stack.pop()
                var num2 = stack.pop()
                var result = when (element) {
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




