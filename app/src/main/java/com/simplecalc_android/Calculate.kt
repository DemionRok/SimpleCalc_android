package com.simplecalc_android

import java.util.*

class Calculate {
    private var operand = ""
    //private var input: String = ""
    private var input = ""
    private var stack = Stack<Float>()
    private var result = 0f

    fun doCalc(v: String): Float {
        var rpn = ReversePolishNotation()
        rpn.transform(v)
        input = rpn.output
        var i = 0
        val size = input.length
        //var rpnInput = listOf<String>()
        for (i in 0 until size) {
            var ch = input[i]
            if (ch.toString() in "0".."999") {
                operand += ch
                stack.push(operand.toFloat())
                operand = ""
            } else {
                var num1 = stack.pop()
                var num2 = stack.pop()
                var result = when (ch) {
                    '+' -> num2 + num1
                    '-' -> num2 - num1
                    '*' -> num2 * num1
                    '/' -> num2 / num1
                    else -> 0
                }
                stack.push(result.toFloat())
            }
        }
       result = stack.pop()
        return result
    }
}




