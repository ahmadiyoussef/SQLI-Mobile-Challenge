package com.example.sqlichallengeapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androiddevs.sqlichallengeapp.util.Resource
import com.example.sqlichallengeapp.MainDispatcherRule
import com.example.sqlichallengeapp.models.User
import com.example.sqlichallengeapp.models.UsersResponse
import com.example.sqlichallengeapp.repository.EmployeesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EmployeesViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<EmployeesRepository>()


    @Test
    fun `Load success`() = runTest {
        // Given
        coEvery { repository.getEmployees() } returns fakeUsers

        // When: call getEmployees
        val viewModel = EmployeesViewModel(repository)
        viewModel.getEmployees()

        // Then: live data should contains success value
        assert(viewModel.employees.value is Resource.Success)
        val usersResponse = (viewModel.employees.value as Resource.Success).data
        Assert.assertEquals(usersResponse, fakeUsers)
    }


    @Test
    fun `Load failed`() = runTest {
        // Given
        coEvery { repository.getEmployees() } returns null

        // When: call getEmployees
        val viewModel = EmployeesViewModel(repository)
        viewModel.getEmployees()

        // Then: live data should contains error value
        assert(viewModel.employees.value is Resource.Error)
    }
}


private val fakeUsers = UsersResponse(
    users = listOf(
        User(
            id = "1",
            firstName = "Fname1",
            lastName = "Lname1",
            email = "emaim1@email.com",
            avatar = "https://avatars.com/1.png"
        ),
        User(
            id = "2",
            firstName = "Fname2",
            lastName = "Lname2",
            email = "emaim2@email.com",
            avatar = "https://avatars.com/2.png"
        ),
        User(
            id = "3",
            firstName = "Fname3",
            lastName = "Lname3",
            email = "emaim3@email.com",
            avatar = "https://avatars.com/3.png"
        )
    )
)