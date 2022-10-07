package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textOutput: TextView
    private lateinit var textOutHistory: TextView
    private var concat: String = " "
    private var operationAux: String = " "
    private var history: String = " "
    private var numberop: Double = 0.0
    private var result: Double = 0.0
    private var numDouble: Double = 0.0
    private var modeResult: Boolean = false
    private var modePoint: Boolean = false
    private var flagPoints: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.textOutput = findViewById(R.id.textView)
        this.textOutHistory = findViewById(R.id.textView2)
    }

    fun onClickNumber(view: View) {
        val valButton = view as Button

        this.concat += valButton.text.toString()

        if(this.modePoint) {
            this.numDouble = this.concat.toDouble()/10
            this.concat = this.numDouble.toString()
            this.modePoint = false
        }

        this.textOutput.text = this.concat
    }

    fun onClickOperation(view: View) {
        val operation = view as Button
        val r: Double

        if(this.concat.length > 1) {

            this.numberop = concat.toDouble()

            if(!modeResult) {
                this.result = this.numberop
                this.modeResult = true
            } else {
                selectOperation(this.numberop, this.operationAux)
            }

            this.concat = " "
            this.numberop = 0.0
            r = this.result

            if(operation.text == "="){
                result()
            }
        } else {
            r = this.result
            this.textOutput.text = this.result.toString()
        }
        this.flagPoints = true
        this.operationAux = operation.text.toString()
        calcHistory(r, operation.text.toString())
    }

    fun onClickDeleteAll(view: View) {
        result()
        this.textOutput.text = this.result.toString()
    }

    fun onClickDelete(view: View) {

        if(this.concat.length > 2) {
            this.concat = this.concat.dropLast(1)
            this.result = this.concat.toDouble()
        } else {
            this.concat = " "
            this.result = 0.0
            this.textOutHistory.text = " "
        }

        this.textOutput.text = this.result.toString()
    }

    fun onClickPoint(view: View) {

        if(this.flagPoints) {
            if(this.concat.length > 1){
                this.modePoint = !this.modePoint
                this.flagPoints = false
            } else {
                this.textOutput.text = this.result.toString()
            }
        }
    }

    private fun selectOperation(number1: Double, operation: String) {

        when (operation) {
            "+" -> this.result = add(number1, this.result)
            "-" -> this.result = sub(number1, this.result)
            "X" -> this.result = mul(number1, this.result)
            "/" -> this.result = div(number1, this.result)

            else -> 0.0
        }

        calcHistory(this.result, operation)
        this.textOutput.text = this.result.toString()
    }

    private fun calcHistory(number: Double, s: String) {
        this.history = " "
        this.history += "$number $s"
        this.textOutHistory.text = this.history
    }

    private fun add(nm1 : Double, nm2 : Double): Double {return nm1 + nm2}
    private fun sub(nm1 : Double, nm2 : Double): Double {return nm2 - nm1}
    private fun mul(nm1 : Double, nm2 : Double): Double {return nm1 * nm2}
    private fun div(nm1 : Double, nm2 : Double): Double {return nm2 / nm1}
    private fun result() {  this.concat = " "
                            this.numberop = 0.0
                            this.operationAux = " "
                            this.textOutHistory.text = " "
                            this.result = 0.0
                            this.modeResult = false
    }
}
