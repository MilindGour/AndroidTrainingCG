package com.example.androidtrainingcg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.time.Duration

class MainActivity : AppCompatActivity() {

    var isEditMode: Boolean = false
    var selectedIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar2)

        // init spinner data
        spinnerDepartments.adapter = ArrayAdapter<Department>(this, android.R.layout.simple_dropdown_item_1line, StateManager.data.departments)

        // load the details, if any
        selectedIndex = intent.getIntExtra("SELECTED_INDEX", -1)
        loadEmployeeData(selectedIndex)

        btnSave.setOnClickListener {
            // saveEmployee()
            finish()
        }
    }

    private fun saveEmployee() {
        if (txtName.text.isNotEmpty() && txtDesignation.text.isNotEmpty() && spinnerDepartments.selectedItemPosition > -1) {
            if (isEditMode) {
//                  update existing record
                StateManager.data.updateEmployee(selectedIndex, txtName.text.toString(), txtDesignation.text.toString(), spinnerDepartments.selectedItemPosition)
            } else {
//                  push new record
                StateManager.data.addEmployee(txtName.text.toString(), txtDesignation.text.toString(), spinnerDepartments.selectedItemPosition)
            }

//            val targetView: View = detailsContainer
//            Snackbar.make(targetView, "Data saved successfully", Snackbar.LENGTH_LONG).show()

            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
            println("Should display a Toast")
        }
    }

    override fun onPause() {
        super.onPause()
        saveEmployee()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println("A menu item is being clicked")

        when(item.itemId) {
            R.id.menuPrev -> return loadNextItem(-1)
            R.id.menuNext -> return loadNextItem(1)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadNextItem(direction: Int): Boolean {
        var newIndex: Int = selectedIndex + direction
        println("New Index: $newIndex")

        // stop if out of bounds
//        if (newIndex >= 0 && newIndex < StateManager.data.employees.size) {
//            selectedIndex = newIndex
//            loadEmployeeData(selectedIndex)
//        }

        // rotate if out of bounds
        if (newIndex < 0) {
            newIndex = StateManager.data.employees.size - 1
        } else if (newIndex >= StateManager.data.employees.size) {
            newIndex = 0
        }
        selectedIndex = newIndex
        loadEmployeeData(selectedIndex)

        return true
    }

    private fun loadEmployeeData(index: Int) {
        isEditMode = index > -1

        if (index > -1) {
//          details page opened in edit mode, show save button (for updating)
            val selectedEmployee: Employee = StateManager.data.employees[index]
            txtName.setText(selectedEmployee.name)
            txtDesignation.setText(selectedEmployee.designation)

//          get index of department
            val deptIndex: Int = StateManager.data.getDepartmentIndexById(selectedEmployee.department.id)
            if (deptIndex > -1) {
                spinnerDepartments.setSelection(deptIndex)
            }
//            btnSave.visibility = View.GONE

        } else {
//            details page opened in add mode, show save button (for adding)
//            btnSave.visibility = View.VISIBLE
        }
    }
}
