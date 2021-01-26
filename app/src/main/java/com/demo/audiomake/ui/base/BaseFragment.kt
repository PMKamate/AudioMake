package com.demo.audiomake.ui.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.fragment.app.Fragment
import com.demo.audiomake.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

/**
 * parent class for all frament to implement the common function of the fragments
 * **/
open class BaseFragment:Fragment() {
    @Suppress("DEPRECATION")
    fun isNetworkAvailable(mContext: Context): Boolean {
        var result = false
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }


    fun showAlert(alertTitle: String, alertMessage: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(alertTitle)
            .setMessage(alertMessage)
            .setPositiveButton(R.string.btn_ok) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    fun showSnackBar(view: View, message: String) {
        Snackbar.make(
            view,
            message, Snackbar.LENGTH_LONG
        ).show()
    }
}