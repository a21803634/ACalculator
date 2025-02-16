package com.example.acalculator;
import android.content.Context
import android.graphics.Path
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_expression.view.*

class HistoryAdapter(context: Context, private val layout: Int, items: ArrayList<String>) : ArrayAdapter<String>(context, layout, items) {

    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getItem(position: Int): String? {
        return super.getItem(position)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(layout,parent, false)
        val expressionParts = getItem(position)?.split("=")
        view.text_expression.text = expressionParts!![0]
        view.text_result.text = expressionParts[1]
        return view
    }


}