package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textOutput: TextView
    private var concat: String = " "
    private var modeResult: Boolean = false
    private var mode: Int = 0
    private var numberop: Double = 0.0
    private var numberResult: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.textOutput = findViewById(R.id.textView)
    }

    fun onClickNumber(view: View) {
        val valButton = view as Button

        concat += valButton.text.toString()
        this.textOutput.text = concat
    }

    fun onClickOperation(view: View) {
        val operation = view as Button

            if(mode != 2) {
                if (modeResult == false) {
                    numberop = concat.toDouble()
                    concat = " "

                    this.textOutput.text = operation.text
                    modeResult = true
                } else {
                    numberResult = numberop
                    numberop = 0.0
                    numberop = concat.toDouble()

                    mode = 2
                    modeResult = false
                }
            } else {
                calcHistory(numberResult, numberop, operation.text.toString())
                mode = 0
            }
        }

        fun calcHistory(number1: Double, number2: Double, operation: String) {

            val result: Double = when (operation) {
                "+" -> add(number1, number2)
                "-" -> sub(number1, number2)
                "X" -> mul(number1, number2)
                "/" -> div(number1, number2)

                else -> 0.0
            }
            concat = result.toString()
            this.textOutput.text = result.toString()
        }

        fun add(nm1 : Double, nm2 : Double): Double {return nm1 + nm2}
        fun sub(nm1 : Double, nm2 : Double): Double {return nm1 - nm2}
        fun mul(nm1 : Double, nm2 : Double): Double {return nm1 * nm2}
        fun div(nm1 : Double, nm2 : Double): Double {return nm1 / nm2}
    }
