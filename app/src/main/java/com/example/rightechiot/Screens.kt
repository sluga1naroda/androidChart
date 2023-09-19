package com.example.rightechiot

import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun dashboard() = FragmentScreen { DashboardsFragment() }
    fun login() = FragmentScreen {LoginFragment() }
}