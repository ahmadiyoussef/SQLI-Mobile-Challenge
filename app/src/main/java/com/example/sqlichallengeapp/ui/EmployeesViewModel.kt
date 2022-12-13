package com.example.sqlichallengeapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.sqlichallengeapp.util.Resource
import com.example.sqlichallengeapp.models.UsersResponse
import com.example.sqlichallengeapp.repository.EmployeesRepository
import kotlinx.coroutines.launch

class EmployeesViewModel(
    private val employeeRepository: EmployeesRepository
) : ViewModel() {

    val employees: MutableLiveData<Resource<UsersResponse>> = MutableLiveData()

    init {
        getEmployees()
    }

    fun getEmployees() = viewModelScope.launch {
        employees.postValue(Resource.Loading())
        val response = employeeRepository.getEmployees()
        if (response != null) {
            employees.postValue(Resource.Success(response))
        } else {
            employees.postValue(Resource.Error("Error fetching users"))
        }
    }
}