package com.example.sooryenapp.presentation.base

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import androidx.fragment.app.Fragment
import com.example.sooryenapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

/**
 * parent class for all frament to implement the common function of the fragments
 * **/
open class BaseFragment:Fragment() {
    fun checkForInternet(): Boolean {
        val cm = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
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