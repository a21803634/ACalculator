package com.example.acalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private var count = ""
    private val VISOR_KEY = "visor"
    private val OPERATOR_KEY = "operator"
    private val LIST_HISTORIC_KEY = "list_historic"
    var ex = ""
    var res = ""
    var operator: Operation = Operation(ex, res)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "O método onCreate foi invocado")
        setContentView(R.layout.activity_main)

        val format = SimpleDateFormat("hh:mm:ss")

        button_0.setOnClickListener { onClickSymbol("0") }
        button_1.setOnClickListener { onClickSymbol("1") }
        button_2.setOnClickListener { onClickSymbol("2") }
        button_3.setOnClickListener { onClickSymbol("3") }
        button_4.setOnClickListener { onClickSymbol("4") }
        button_5.setOnClickListener { onClickSymbol("5") }
        button_6.setOnClickListener { onClickSymbol("6") }
        button_7.setOnClickListener { onClickSymbol("7") }
        button_8.setOnClickListener { onClickSymbol("8") }
        button_9.setOnClickListener { onClickSymbol("9") }
        button_decimal.setOnClickListener { onClickAction(".") }
        button_addiction.setOnClickListener { onClickAction("+") }
        button_subtraction.setOnClickListener { onClickAction("-") }
        button_multiplication.setOnClickListener { onClickAction("*") }
        button_division.setOnClickListener { onClickSymbol("/") }
        button_equals.setOnClickListener { onClickEquals() }
        button_clearer.setOnClickListener { onClickClearer() }
        button_deleter.setOnClickListener { onClickDeleter() }
        button_result.setOnClickListener { onClickResult() }

        Toast.makeText(
            this,
            "${format.format(Date())} Ciclo de Vida: onCreate()",
            Toast.LENGTH_SHORT
        ).show()

    }

    private fun onClickSymbol(symbol: String) {
        if (text_visor.text == "0") {
            text_visor.text = symbol
        } else {
            text_visor.append(symbol)
        }
    }

    private fun onClickEquals() {
        ex = text_visor.text.toString()
        count = text_visor.text.toString() + " = "
        val expression = ExpressionBuilder(text_visor.text.toString()).build()
        text_visor.text = expression.evaluate().toString()
        res = text_visor.text.toString()
        operator.setVariables(ex,res)
        operator.populateList()
        list_historic.adapter =
            HistoryAdapter(this, R.layout.item_expression, operator.lista)
        count += text_visor.text
    }

    private fun onClickAction(symbol: String) {
        text_visor.append(symbol)
    }

    private fun onClickClearer() {
        text_visor.text = "0"
    }

    private fun onClickDeleter() {
        text_visor.text = text_visor.text.substring(0, text_visor.text.length - 1)
    }

    private fun onClickResult() {
        if (text_visor.text == "0") {
        } else {
            text_visor.text = count
        }
    }

    override fun onDestroy() {
        Log.e(TAG, "O método onDestroy foi invocado")
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        text_visor.text = savedInstanceState?.getString(VISOR_KEY)
        operator.lista = savedInstanceState?.getStringArrayList(OPERATOR_KEY) as ArrayList<String>
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putString(VISOR_KEY, text_visor.text.toString()) }
        outState.run { putStringArrayList(OPERATOR_KEY, operator.lista) }
        super.onSaveInstanceState(outState)
    }


}

