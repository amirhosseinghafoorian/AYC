package com.a.ayc.user.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a.ayc.user.domain.SignUpUseCase
import com.google.firebase.auth.FirebaseUser

class SignUpViewModel
@ViewModelInject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val currentUser = MutableLiveData<FirebaseUser?>()
    val isSignedIn = MutableLiveData<Boolean?>()

    fun validateEmail(email: String) = signUpUseCase.validateEmail(email)

    fun validatePassword(password: String) = signUpUseCase.validatePassword(password)

    fun signUp(email: String, password: String) {
        val result = signUpUseCase.signUp(email, password)
        if (result == null) isSignedIn.postValue(false)
        else isSignedIn.postValue(true)
        currentUser.postValue(result)
    }

}