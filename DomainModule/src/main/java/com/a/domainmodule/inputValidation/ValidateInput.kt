package com.a.domainmodule.inputValidation

import android.util.Log
import android.util.Patterns

class ValidateInput {

    fun checkEmailValidation(email: String): Boolean {
        if (email.isEmpty()) {
            Log.i("baby", "Email address can not be empty")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Log.i("baby", "Enter a valid email address")
            return false
        }
        Log.i("baby", "Email Confirmed")
        return true
    }

    fun checkPasswordValidation(password: String): Boolean {
        if (password.isEmpty()) {
            Log.i("baby", "Password can not be empty")
            return false
        } else if (password.length < 6) {
            Log.i("baby", "Enter a password with at least 6 characters")
            return false
        }
        Log.i("baby", "password confirmed")
        return true
    }

    fun checkTheSamePassword(password: String, repeatPassword: String): Boolean {
        return if (password == repeatPassword) true
        else {
            Log.i("baby", "passwords don't match")
            false
        }
    }

    fun checkNameValidation(name: String): Boolean {
        if (name.isEmpty()) {
            Log.i("baby", "Name can not be empty")
            return false
        } else if (name.length < 4) {
            Log.i("baby", "Enter a name with at least 4 characters")
            return false
        }
        return true
    }

}