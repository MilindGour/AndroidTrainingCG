package com.example.androidtrainingcg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_person_details.*

class PersonDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        // fetch object from the intent
        val incomingObject: PersonData = intent.getSerializableExtra("PERSON_DATA_OBJECT") as PersonData
        tvID.text = incomingObject.id.toString()
        tvName.text = incomingObject.name
        tvGender.text = incomingObject.gender
        tvEmail.text = incomingObject.email
    }
}
