package com.example.acalculator

import java.util.ArrayList

class Operation(var expression: String, var result: String) {
    var lista = ArrayList<String>()


    fun populateList(){
        lista.add("${this.expression} = ${this.result}")
    }

    fun setVariables(expression: String, result: String){
        this.expression = expression
        this.result = result
    }

}