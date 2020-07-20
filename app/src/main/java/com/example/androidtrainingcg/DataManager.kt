package com.example.androidtrainingcg

class DataManager {
    val departments: ArrayList<Department> = ArrayList<Department>()
    val employees: ArrayList<Employee> = ArrayList<Employee>()

    init {
        // init departments
        departments.clear()
        departments.add(Department(10, "Human Resources"))
        departments.add(Department(20, "Development"))
        departments.add(Department(30, "Accounts"))
        departments.add(Department(40, "Information Technology"))

        // init employees
        employees.add(Employee(100, "Milind", "Consultant", departments[1]))
        employees.add(Employee(101, "Stephen", "Associate Consultant", departments[3]))
        employees.add(Employee(102, "Joseph", "Senior Consultant", departments[0]))
    }

    public fun getDepartmentIndexById(id: Int): Int {
        return departments.indexOfFirst { it.id == id }
    }

    public fun addEmployee(name: String, designation: String, departmentIndex: Int) {
        employees.add(Employee(employees.last().id + 1, name, designation, departments[departmentIndex]))
    }

    public fun updateEmployee(index: Int, name: String, designation: String, departmentIndex: Int) {
        var targetObject: Employee = Employee(employees[index].id, name, designation, departments[departmentIndex])
        employees[index] = targetObject
    }
}

class Department(val id: Int, val name: String) {
    override fun toString(): String {
        return "[$id] $name"
    }
}
class Employee(val id: Int, val name: String, val designation: String, val department: Department) {
    override fun toString(): String {
        return "$name\nID: $id\nDesignation: $designation\nDepartment: ${department.name}"
    }
}