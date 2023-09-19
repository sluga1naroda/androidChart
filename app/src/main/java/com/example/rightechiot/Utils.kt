package com.example.rightechiot

import android.app.Activity
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router

val Activity.router: Router
    get() {
        return (application as RighTechApplication).router
    }

val Fragment.router: Router
    get() {
        return (activity as MainActivity).router
    }
val Activity.customRetrofit: APIInterface
    get() {
        return (application as RighTechApplication).retrofit
    }

val Fragment.retrofit: APIInterface
    get() {
        return (activity as MainActivity).customRetrofit
    }