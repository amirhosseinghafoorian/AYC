package com.a.ayc.user.domain

import com.a.ayc.user.data.UserRemote
import com.a.ayc.user.data.ValidateInput
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val validateInput: ValidateInput,
    private val userRemote: UserRemote
) {

    fun validateEmail(email: String) = validateInput.checkEmailValidation(email)

    fun validatePassword(password: String) = validateInput.checkPasswordValidation(password)

    fun signUp(email: String, password: String) = userRemote.signUp(email, password)

}