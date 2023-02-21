package com.simplecalc_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_clear.setOnClickListener {
            input.text = ""
            output.text = ""
        }
        button_delete.setOnClickListener {
            input.text = removeLastChar(input.text.toString());
        }
        button_0.setOnClickListener {
            input.text = addToInputText("0")
        }
        button_1.setOnClickListener {
            input.text = addToInputText("1")
        }
        button_2.setOnClickListener {
            input.text = addToInputText("2")
        }
        button_3.setOnClickListener {
            input.text = addToInputText("3")
        }
        button_4.setOnClickListener {
            input.text = addToInputText("4")
        }
        button_5.setOnClickListener {
            input.text = addToInputText("5")
        }
        button_6.setOnClickListener {
            input.text = addToInputText("6")
        }
        button_7.setOnClickListener {
            input.text = addToInputText("7")
        }
        button_8.setOnClickListener {
            input.text = addToInputText("8")
        }
        button_9.setOnClickListener {
            input.text = addToInputText("9")
        }
        button_dot.setOnClickListener {
            input.text = addToInputText(".")
        }
        button_division.setOnClickListener {
            input.text = addToInputText("÷")
        }
        button_multiply.setOnClickListener {
            input.text = addToInputText("×")
        }
        button_subtraction.setOnClickListener {
            input.text = addToInputText("-")
        }
        button_addition.setOnClickListener {
            input.text = addToInputText("+")
        }
        button_brackets.setOnClickListener {
            if (input.text.isEmpty()) {
                input.text = addToInputText("(")
            } else {
                input.text = addToInputText(")")
            }
        }
        button_equals.setOnClickListener {
            showResult()
        }
    }

    private fun getInputExpression(): String {
        var expression = input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    @SuppressLint("SetTextI18n")
    private fun showResult() = try {
        val expression = getInputExpression()
        val result = Calculate().doCalc(expression)
        if (result.isNaN()) {
            output.text = "Error"
            output.setTextColor(ContextCompat.getColor(this, R.color.green))
        } else {
            output.text = DecimalFormat("0.######").format(result).toString()
            output.setTextColor(ContextCompat.getColor(this, R.color.green))
        }
    } catch (e: Exception) {
        output.text = "Error"
        output.setTextColor(ContextCompat.getColor(this, R.color.red))
    }

    private fun addToInputText(buttonValue: String): String {
        return "${input.text}$buttonValue"
    }

    private fun removeLastChar(str: String?): String? {
        return str?.replaceFirst(".$".toRegex(), "")
    }
}

