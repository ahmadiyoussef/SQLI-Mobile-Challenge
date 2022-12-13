package com.example.sqlichallengeapp.repository

import com.example.sqlichallengeapp.api.RetrofitInstance
import com.example.sqlichallengeapp.models.UsersResponse

class EmployeesRepository() {

    suspend fun getEmployees(): UsersResponse? {
        return try {
            RetrofitInstance.api.getEmployees()
        } catch (e: Exception) {
            null
        }
    }
}