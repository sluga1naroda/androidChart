package com.example.rightechiot

import com.example.rightechiot.chooseServer.ChooseServerFragment
import com.example.rightechiot.dashboard.DashboardFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun dashboards() = FragmentScreen { DashboardsFragment() }
    fun login() = FragmentScreen { LoginFragment() }

    fun dashboard() = FragmentScreen { DashboardFragment() }
    fun chooseServer() = FragmentScreen { ChooseServerFragment() }
}