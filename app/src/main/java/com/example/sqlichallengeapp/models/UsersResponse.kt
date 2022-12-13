package com.example.sqlichallengeapp.models

import com.example.sqlichallengeapp.models.User
import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("data")
    val users: List<User>

)