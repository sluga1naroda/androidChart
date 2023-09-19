package com.example.rightechiot


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @POST("/api/v1/auth/token")
    fun requestLogin(@Body requestModel: RequestModel) : Call<ResponseModel>

    @POST("/api/v1/models") // создать модель
    fun createModel(@Body uniqueModel: UniqueModel) : Call<Void>

    @GET("/api/v1/models") // получить список
    fun getModels() : Call<ArrayOfUniqueModel>

}