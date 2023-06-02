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

        if(ispressedEqual){
            var inputText:TextView = findViewById(R.id.inputText)
            inputText.text=""
            ispressedEqual = false
        }

        var inputText:TextView = findViewById(R.id.inputText)

        inputText.append((view as Button).text)
        numberTyped = true
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
            (inputTextView.contains("-")||inputTextView.contains("+")
                    ||inputTextView.contains("*")||inputTextView.contains("/"))
        }
    }

    fun onOpertator(view: View){
        if(!isOperatorAdd()&&numberTyped||!programStarted){
            var inputText:TextView = findViewById(R.id.inputText)
            inputText.append((view as Button).text)

            ispressedEqual = false

            decimalPoint = false
            numberTyped = false
            programStarted = true
        }
    }

    fun onEqual(view: View) {

        ispressedEqual = true

        if(numberTyped){
            var inputText:TextView = findViewById(R.id.inputText)
            var inputTextView = inputText.text.toString()

            var prefix = ""
            try{
                if(inputTextView.startsWith("-")){
                    prefix="-"
                    inputTextView = inputTextView.substring(1)
                }

                if (inputTextView.contains("-")){
                    val splitValues = inputTextView.split("-")

                    var one = splitValues[0]  //27,28
                    var two = splitValues[1] //27-24


                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }

                    inputText.text = (one.toDouble() - two.toDouble()).toString()
                }

                if (inputTextView.contains("+")){
                    val splitValues = inputTextView.split("+")

                    var one = splitValues[0]  //27,28
                    var two = splitValues[1] //27-24


                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }

                    inputText.text = (one.toDouble() + two.toDouble()).toString()
                }

                if (inputTextView.contains("*")){
                    val splitValues = inputTextView.split("*")

                    var one = splitValues[0]  //27,28
                    var two = splitValues[1] //27-24


                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }

                    inputText.text = (one.toDouble() * two.toDouble()).toString()
                }

                if (inputTextView.contains("/")){
                    val splitValues = inputTextView.split("/")

                    var one = splitValues[0]  //27,28
                    var two = splitValues[1] //27-24


                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }

                    inputText.text = (one.toDouble() / two.toDouble()).toString()
                }

            }catch (e: java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}