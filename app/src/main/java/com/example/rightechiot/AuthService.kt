package com.example.rightechiot

import android.content.Context

interface AuthService {
    fun saveToken(token: String)
    fun logout()

    val isAuth: Boolean
    val token: String?

}

class AuthServiceImpl(context: Context) : AuthService {

    private val sharedPreference = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    override val isAuth: Boolean
        get() {
            val token = sharedPreference.getString("auth_token", "").orEmpty()
            return token.isNotEmpty()
        }
    override val token: String?
        get() = sharedPreference.getString("auth_token",null)

    override fun saveToken(token: String) {
        sharedPreference.edit()
            .putString("auth_token", token)
            .apply()
    }

    override fun logout() {
        sharedPreference.edit().clear().apply()
    }
}

fun AuthService(): AuthService = AuthServiceImpl(RighTechApplication.INSTANCE)