package com.a.ayc.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.a.ayc.user.domain.SignUpUseCase
import com.google.firebase.auth.FirebaseUser

class HomeViewModel
@ViewModelInject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    fun currentUser(): FirebaseUser? = signUpUseCase.currentUser()

    fun logout() = signUpUseCase.logout()
}