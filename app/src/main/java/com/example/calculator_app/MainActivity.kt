package com.example.calculator_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var decimalPoint = false
    private var startedMinus = false
    private var numberTyped = false
    private var programStarted = false
    var ispressedEqual = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickDigit(view: View) {
        var inputText:TextView = findViewById(R.id.inputText)

        inputText.append((view as Button).text)
    }

    fun onClickClear(view: View){
        var inputText:TextView = findViewById(R.id.inputText)

        inputText.text = ""

    }

    fun DecimalPoint(view: View){
        if (!decimalPoint){
            var inputText:TextView = findViewById(R.id.inputText)
            inputText.append((view as Button).text)
            decimalPoint = true
        }
    }

    fun isOperatorAdd(): Boolean {
        var inputText:TextView = findViewById(R.id.inputText)

        var inputTextView = inputText.text.toString()

        var minuses = inputTextView.split("-")//99-87

        startedMinus = minuses.size<3

//        -45

        return if (inputTextView.startsWith("-")&&startedMinus){
            false
        }else{
            (inputTextView.contains("−")||inputTextView.contains("+")
                    ||inputTextView.contains("×")||inputTextView.contains("÷"))
        }
    }

    fun onOpertator(view: View){
        if(!isOperatorAdd()&&numberTyped||!programStarted){
            var inputText:TextView = findViewById(R.id.inputText)
            inputText.append((view as Button).text)

            decimalPoint = false
            numberTyped = false
            programStarted = true
        }
    }
}