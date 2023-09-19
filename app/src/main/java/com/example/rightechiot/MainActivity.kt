package com.example.rightechiot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Response
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.rightechiot.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.navigation.NavigationView
import javax.security.auth.callback.Callback

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
                    router.newRootScreen(Screens.dashboard())
                }
                R.id.nav_settings -> {
                    router.newRootScreen(Screens.login())
                }
            }
            true
        }


        if(authService.isAuth) {
            showHideNavView(navView)
            router.newRootScreen(Screens.dashboard())
        } else {
            showHideNavView(navView)
            router.newRootScreen(Screens.login())
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