package com.example.toeicpreparation.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.toeicpreparation.R
import com.example.toeicpreparation.ui.activity.HomeActivity
import com.example.toeicpreparation.ui.viewmodel.AuthViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var authViewModel: AuthViewModel
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button

    private lateinit var errorUsername: TextView
    private lateinit var errorPassword: TextView

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        edtUsername = view.findViewById(R.id.usernameInput)
        edtPassword = view.findViewById(R.id.passwordInput)

        errorUsername = view.findViewById(R.id.errorUsername)
        errorPassword = view.findViewById(R.id.errorPassword)

        btnLogin = view.findViewById(R.id.btn_login)

        progressBar = view.findViewById(R.id.progressBar)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        btnLogin.setOnClickListener {

            var isValid = true

            if (edtUsername.text.isNullOrBlank()) {
                errorUsername.text = "Tên đăng nhập không được để trống"
                errorUsername.visibility = View.VISIBLE
                isValid = false
            } else {
                errorUsername.visibility = View.GONE
            }

            if (edtPassword.text.isNullOrBlank()) {
                errorPassword.text = "Mật khẩu không được để trống"
                errorPassword.visibility = View.VISIBLE
                isValid = false
            } else {
                errorPassword.visibility = View.GONE
            }

            if (isValid) {
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()
                authViewModel.login(requireContext(), username, password)
            }
        }

        authViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            btnLogin.isEnabled = !isLoading
        }

        authViewModel.loginResult.observe(viewLifecycleOwner, { result ->
            result.onSuccess {
                val intent = Intent(context, HomeActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }.onFailure { exception ->
                Toast.makeText(context, "Login failed: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
