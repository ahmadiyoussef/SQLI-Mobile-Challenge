package com.example.sqlichallengeapp.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.sqlichallengeapp.util.Resource
import com.example.sqlichallengeapp.R
import com.example.sqlichallengeapp.adapters.UsersAdapter
import com.example.sqlichallengeapp.models.UsersResponse
import com.example.sqlichallengeapp.repository.EmployeesRepository
import com.example.sqlichallengeapp.util.Tools
import kotlinx.android.synthetic.main.activity_employee.*


class EmployeesActivity : AppCompatActivity() {

    lateinit var viewModel: EmployeesViewModel
    private lateinit var adapter: UsersAdapter
    private var dialogProgress: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)
        adapter = UsersAdapter()
        recyclerView.adapter = adapter
        val newsRepository = EmployeesRepository()
        val viewModelProviderFactory = EmployeeViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(
            this, viewModelProviderFactory
        ).get(EmployeesViewModel::class.java)

        viewModel.getEmployees()
        dialogProgress = Tools.setProgressDialog(this)

        viewModel.employees.observe(this, Observer { resource: Resource<UsersResponse> ->
            if (resource is Resource.Success) {
                dialogProgress?.dismiss()
                val users = resource.data?.users.orEmpty()
                adapter.differ.submitList(users)
            }
            if (resource is Resource.Loading) {
                dialogProgress?.show()
            }
            if (resource is Resource.Error) {

            }
        })
    }
}
