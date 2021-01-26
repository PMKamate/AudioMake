package com.demo.audiomake.ui.navigator

/**
 * interface to communicate viewmodel with fragment
 * */
interface HomeNavigator {
    fun showError(title:String,message:String)
    fun updateSync(value:Boolean=true)

}