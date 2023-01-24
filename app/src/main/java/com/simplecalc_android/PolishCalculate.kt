package com.simplecalc_android

import java.util.*

class PolishCalculate {

    private val operationMap = mapOf<String,(Double, Double) -> Double>(
        "+" to {x, y -> x + y},
        "-" to {x, y -> x - y},
        "*" to {x, y -> x + y},
        "/" to {x, y -> x + y},
    )

    private fun Stack<Double>.execute(op: (Double, Double) -> Double): Double = push(op(pop(), pop()))

    fun polishCalculate():Double{
        val expression = "5 + 2"
        val stack = Stack<Double>()
        for (arg in expression.split(' ')) {
            val op = operationMap[arg]
            if (op != null) {
                stack.execute(op)
            } else {
                stack.push(arg.toDouble())
            }
        }
        return stack.pop()

    }

    fun polishCalculateFunctional():Double {
        val expression = "5+2"
        val stack = Stack<Double>()
        expression.split(".").forEach() {
            operationMap[it]?.let { op -> stack.execute(op) } ?: stack.push(it.toDouble())
        }
        return stack.pop()
    }
}