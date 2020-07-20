package com.example.androidtrainingcg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listViewEmployees.adapter = ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, StateManager.data.employees)

        btnAddNew.setOnClickListener {
            navigateToDetailsPage(-1)
        }

        listViewEmployees.setOnItemClickListener { _, _, i, _ ->
            navigateToDetailsPage(i)
        }
    }

    override fun onResume() {
        super.onResume()
//        listViewEmployees.adapter = ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, StateManager.data.employees)
        (listViewEmployees.adapter as ArrayAdapter<Employee>).notifyDataSetChanged()
    }

    fun navigateToDetailsPage(index: Int) {
        val navIntent: Intent = Intent(this, MainActivity::class.java).putExtra("SELECTED_INDEX", index)
        startActivity(navIntent)
    }
}
