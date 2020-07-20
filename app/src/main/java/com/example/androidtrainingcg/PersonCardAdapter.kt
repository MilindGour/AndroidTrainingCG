package com.example.androidtrainingcg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TableLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.person_card_row.view.*

class PersonCardAdapter(private val personList: ArrayList<PersonData>): RecyclerView.Adapter<PersonCardAdapter.PersonCardViewHolder>() {

    var maleColor: Int = -1
    var femaleColor: Int = -1
    var personClickListener: (PersonData)->Unit = { _-> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonCardAdapter.PersonCardViewHolder {
        maleColor = ContextCompat.getColor(parent.context, R.color.colorMale)
        femaleColor = ContextCompat.getColor(parent.context, R.color.colorFemale)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_card_row, parent, false)
        return PersonCardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonCardAdapter.PersonCardViewHolder, position: Int) {
        val targetPerson: PersonData = personList[position]
        holder.bindPerson(targetPerson)
    }

    fun getSelectedItems(): List<PersonData> {
        return personList.filter { it.isChecked }
    }

    fun selectAllItems() {
        personList.forEach {
            it.isChecked = true
        }
        notifyDataSetChanged()
    }

    fun selectNoneItems() {
        personList.forEach {
            it.isChecked = false
        }
        notifyDataSetChanged()
    }

    fun setOnPersonClickListener(listener: (PersonData) -> Unit) {
        personClickListener = listener
    }

    inner class PersonCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var personName: TextView = itemView.personName
        var personEmail: TextView = itemView.personEmail
        var personCheckbox: CheckBox = itemView.personCheckbox
        var cardTable: TableLayout = itemView.cardTable

        fun bindPerson(p: PersonData) {
            personName.text = p.name
            personEmail.text = p.email
            cardTable.setBackgroundColor(if(p.gender == "male") maleColor else femaleColor)

            personCheckbox.setOnCheckedChangeListener(null)
            personCheckbox.isChecked = p.isChecked
            personCheckbox.setOnCheckedChangeListener { _, bool: Boolean ->
                p.isChecked = bool
            }

            cardTable.setOnClickListener {
                personClickListener(p)
            }
        }
    }
}