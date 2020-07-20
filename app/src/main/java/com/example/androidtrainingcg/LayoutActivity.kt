package com.example.androidtrainingcg

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow.*
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import kotlinx.android.synthetic.main.activity_layout.*

class LayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        val tableData: List<Person> = TableData().data
        for (rowData in tableData) {
            val tableRow: TableRow = TableRow(this)
            tableRow.addView(getCellView(rowData.id))
            tableRow.addView(getCellView(rowData.name))
            tableRow.addView(getCellView(rowData.email))

            theTable.addView(tableRow)
        }
    }

    fun getCellView(text: String): TextView {
        var tv: TextView = TextView(this)
        var lp: LayoutParams = LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)
        lp.setMargins(1)
        tv.layoutParams = lp
        tv.setBackgroundColor(Color.WHITE)
        tv.setText(text)
        tv.setPadding(10)

        return tv
    }
}


class TableData {
    public val data: List<Person> = listOf<Person>(
        Person("1", "Jasmine Copeland", "jasminecopeland@omatom.com"),
        Person("2", "Aurelia Hansen", "aureliahansen@omatom.com"),
        Person("3", "Frances Joyce", "francesjoyce@omatom.com"),
        Person("4", "Louisa Dillard", "louisadillard@omatom.com"),
        Person("5", "Meghan Martin", "meghanmartin@omatom.com"),
        Person("6", "Jennie Jennings", "jenniejennings@omatom.com"),
        Person("7", "Fox Bowen", "foxbowen@omatom.com"),
        Person("8", "Mayra Everett", "mayraeverett@omatom.com"),
        Person("9", "Joan Richmond", "joanrichmond@omatom.com"),
        Person("10", "Dale Atkinson", "daleatkinson@omatom.com")
    )
}

data class Person(val id: String, val name: String, val email: String)