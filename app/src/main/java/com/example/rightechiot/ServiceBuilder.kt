package com.example.rightechiot

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://sandbox.rightech.io")
        .addConverterFactory(GsonConverterFactory.create())

        .client(client)
        .build()
    fun <T>buildService(service:Class<T>) : T{
        return retrofit.create(service)
    }
}

class AuthInterceptor() : Interceptor {

    private val authService = AuthService()

    override fun intercept(chain: Interceptor.Chain) : Response{

        if (!authService.isAuth) return chain.proceed(chain.request())
        val token = authService.token!!
        val request = chain.request()

        return chain.proceed(request.newBuilder().addHeader("Authorization", "Bearer $token").build())
    }

}