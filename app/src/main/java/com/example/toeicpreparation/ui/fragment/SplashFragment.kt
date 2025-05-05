package com.example.toeicpreparation.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toeicpreparation.R

class SplashFragment : Fragment() {

    private var loadingDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        // Show loading
        showLoading()

        Handler(Looper.getMainLooper()).postDelayed({
            hideLoading()
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainLayout, StartFragment())
                .commit()
        }, 3000)

        return view
    }

    private fun showLoading() {
        val view = layoutInflater.inflate(R.layout.dialog_loading, null)
        loadingDialog = AlertDialog.Builder(requireContext())
            .setView(view)
            .setCancelable(false)
            .create()
        loadingDialog?.show()
    }

    private fun hideLoading() {
        loadingDialog?.dismiss()
    }
}
