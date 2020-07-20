package com.example.androidtrainingcg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_expo.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable

class RecyclerExpo : AppCompatActivity() {

    var personList: ArrayList<PersonData> = ArrayList<PersonData>()
    private val personCardAdapter = PersonCardAdapter(personList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_expo)

        loadJSONDataFromAssets()

        var theLayoutManager = LinearLayoutManager(this)
        Log.d("OnCreate", "PersonList size: ${personList.size}")
        personRecyclerView.apply {
            layoutManager = theLayoutManager
            adapter = personCardAdapter
        }

        // onPersonClick listener
        personCardAdapter.setOnPersonClickListener {
            Toast.makeText(this, "${it.name}(${it.email})", Toast.LENGTH_SHORT).show()

            var navIntent: Intent = Intent(this, PersonDetails::class.java)
            navIntent.putExtra("PERSON_DATA_OBJECT", it)
            startActivity(navIntent)
        }

        // fabOK click listener
        fabOK.setOnClickListener {
            Toast.makeText(this, "Total Persons selected: ${personCardAdapter.getSelectedItems().size}", Toast.LENGTH_SHORT).show()
        }

        // fabSelectAll click listener
        fabSelectAll.setOnClickListener {
            personCardAdapter.selectAllItems()
        }

        // fabSelectNone click listener
        fabSelectNone.setOnClickListener {
            personCardAdapter.selectNoneItems()
        }
    }

    private fun loadJSONDataFromAssets() {
        val inputStream = assets.open("person_data.json")
        var byteArray: ByteArray = ByteArray(inputStream.available())
        inputStream.read(byteArray)
        inputStream.close()

        val jsonString: String = String(byteArray)
        val rootObjectJSON: JSONObject = JSONObject(jsonString)
        val dataArrayJSON: JSONArray = rootObjectJSON.getJSONArray("data")

        for (index in 0 until dataArrayJSON.length()) {
            val personJSON: JSONObject = dataArrayJSON.getJSONObject(index)
            personList.add(PersonData(personJSON.getInt("id"), personJSON.getString("name"), personJSON.getString("gender"), personJSON.getString("email"), false))
        }
    }
}

data class PersonData(val id: Int, val name: String, val gender: String, val email: String, var isChecked: Boolean): Serializable {
//    override fun toString(): String {
//        return "$id: $name"
//    }
}
