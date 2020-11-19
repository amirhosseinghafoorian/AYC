package com.a.ayc.user.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.a.ayc.user.domain.SignUpUseCase

class SignUpViewModel
@ViewModelInject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    fun validateEmail(email : String) = signUpUseCase.validateEmail(email)

}