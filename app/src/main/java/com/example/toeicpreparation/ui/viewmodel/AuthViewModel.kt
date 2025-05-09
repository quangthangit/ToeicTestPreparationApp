package com.example.toeicpreparation.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toeicpreparation.data.remote.LoginRequest
import com.example.toeicpreparation.data.remote.LoginResponse
import com.example.toeicpreparation.data.repository.AuthRepository

class AuthViewModel : ViewModel() {
    private var repository: AuthRepository? = null

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult

    fun login(context: Context, username: String, password: String) {
        repository = AuthRepository(context)
        val request = LoginRequest(username, password)
        repository?.login(request)?.observeForever {
            _loginResult.value = it
        }
    }
}

