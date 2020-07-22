package com.example.sooryenapp.presentation.navigator

/**
 * interface to communicate viewmodel with fragment
 * */
interface HomeNavigator {
    fun showError(title:String,message:String)
    fun updateSync(value:Boolean=true)

}