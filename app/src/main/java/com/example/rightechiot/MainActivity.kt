package com.example.rightechiot

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentActivity
import com.example.rightechiot.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.navigation.NavigationView

class MainActivity : FragmentActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var navView : NavigationView

    val authService = AuthService()
    private val navigator = AppNavigator(this, R.id.place_holder)

    override fun onResumeFragments() {
        super.onResumeFragments()
        RighTechApplication.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onDestroy() {
        authService.logout()
        super.onDestroy()
    }
    override fun onPause() {
        RighTechApplication.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        navView = binding.navView
        val drawerLayout = binding.drawerLayout
        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        actionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_logout -> {
                    authService.logout()
                }
                R.id.nav_dashboards -> {
                    router.newRootScreen(Screens.dashboards())
                }
                R.id.nav_settings -> {
                    router.newRootScreen(Screens.login())
                }
            }
            true
        }


        if(authService.isAuth) {
            showHideNavView(navView)
            router.newRootScreen(Screens.dashboards())
        } else {
            showHideNavView(navView)
            router.newRootScreen(Screens.dashboard())
        }
        setContentView(binding.root)
    }

    fun showHideNavView(view:View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.INVISIBLE
        } else{
            View.GONE
        }
    }
}