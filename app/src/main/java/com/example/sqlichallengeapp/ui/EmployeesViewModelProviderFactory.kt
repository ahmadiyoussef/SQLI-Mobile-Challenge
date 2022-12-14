package com.example.sqlichallengeapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sqlichallengeapp.repository.EmployeesRepository

class EmployeeViewModelProviderFactory(
    private val repository: EmployeesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EmployeesViewModel(repository) as T
    }
}

