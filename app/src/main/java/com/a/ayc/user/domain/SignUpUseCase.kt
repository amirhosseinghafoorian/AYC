package com.a.ayc.user.domain

import com.a.ayc.home.data.HomeRemote
import com.a.ayc.user.data.UserRemote
import com.a.ayc.user.data.ValidateInput
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val validateInput: ValidateInput,
    private val userRemote: UserRemote,
    private val homeRemote: HomeRemote
) {

    fun validateEmail(email: String) = validateInput.checkEmailValidation(email)

    fun validatePassword(password: String) = validateInput.checkPasswordValidation(password)

    fun signUp(email: String, password: String): Task<AuthResult>? = userRemote.signUp(email, password)

    fun currentUser() : FirebaseUser? = homeRemote.currentUser()
}