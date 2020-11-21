package com.a.ayc.user.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a.ayc.user.domain.SignUpUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

class SignUpViewModel
@ViewModelInject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    val currentUser = MutableLiveData<Task<AuthResult>>()

    fun validateEmail(email: String) = signUpUseCase.validateEmail(email)

    fun validatePassword(password: String) = signUpUseCase.validatePassword(password)

    fun signUp(email: String, password: String) {
        val result = signUpUseCase.signUp(email, password)
        result?.addOnCompleteListener {
            currentUser.postValue(result)
        }
    }

    fun currentUser() : FirebaseUser? = signUpUseCase.currentUser()
}