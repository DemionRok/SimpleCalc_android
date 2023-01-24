package com.simplecalc_android

import java.util.*


class ReversePolishNotation() {

    private var stack = Stack<String>()
    var output = ""

    fun transform(s:String) {
       /* val pattern = Pattern.compile("([0-9]+)|(\\+|\\-|\\*|\\/)")
        val matcher = pattern.matcher(s)*/
        //var rpnArray:Array<Char> = arrayOf()
        //s.split("\\+")
       /* var out = listOf<Float>()
        for (i in s) {
            out = listOf(s[i.toInt()].toFloat())
        }*/

        for (element in s) {
            when (element) {
                '+' -> getOperation(element, 1)
                '-' -> getOperation(element, 1)
                '*' -> getOperation(element, 2)
                '/' -> getOperation(element, 2)
                '(' -> stack.push(element.toString())
                ')' -> gotParen()
                else -> output += element
            }
        }
        while (!stack.isEmpty()) {
            output += stack.pop().toFloat()
        }
    }

    private fun gotParen() {
        while (!stack.isEmpty()) {
            val element = stack.pop()
            if (element == "(") {
                break
            } else {
                output += element.toFloat()
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
                var oldPriority = -1
                oldPriority = if (opTop == "+" || opTop == "-") 1 else 2
                if (oldPriority < priority) {
                    stack.push(opTop)
                    break
                } else output += opTop.toFloat()
            }
        }
        stack.push(currentOp.toString())
    }
}




