package com.example.toeicpreparation.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.toeicpreparation.R
import com.example.toeicpreparation.ui.activity.HomeActivity
import com.example.toeicpreparation.ui.viewmodel.AuthViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var authViewModel: AuthViewModel
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button

    private lateinit var errorUsername: TextView
    private lateinit var errorPassword: TextView

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
                Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
            }

            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            authViewModel.login(username, password)
        }

        authViewModel.loginResult.observe(viewLifecycleOwner, { result ->
            result.onSuccess {
                val intent = Intent(context, HomeActivity::class.java)
                startActivity(intent)
            }.onFailure {

            }
        })
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
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