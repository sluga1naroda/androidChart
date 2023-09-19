package com.example.rightechiot

data class ResponseModel(
    val token: String,
    val user: User
)
data class UniqueModel(
    val base : String,
    val name : String,
    val description : String
)
data class ArrayOfUniqueModel(
    val array : MutableList<UniqueModel>
)