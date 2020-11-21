package com.a.ayc.user.data

import android.util.Patterns
import android.widget.Toast
import com.a.ayc.MyApp

class ValidateInput {

    fun checkEmailValidation(email: String): Boolean {
        if (email.isEmpty()) {
            Toast.makeText(MyApp.publicApp, "Email address can not be empty", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(MyApp.publicApp, "Enter a valid email address", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        Toast.makeText(MyApp.publicApp, "Email Confirmed", Toast.LENGTH_SHORT)
            .show()
        return true
    }

    fun checkPasswordValidation(password: String): Boolean {
        if (password.isEmpty()) {
            Toast.makeText(MyApp.publicApp, "Password can not be empty", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (password.length < 6) {
            Toast.makeText(
                MyApp.publicApp,
                "Enter a password with at least 6 characters",
                Toast.LENGTH_SHORT
            )
                .show()
            return false
        }
        Toast.makeText(MyApp.publicApp, "password confirmed", Toast.LENGTH_SHORT)
            .show()
        return true
    }

    fun checkTheSamePassword(password: String, repeatPassword: String): Boolean {
        return if (password == repeatPassword) true
        else {
            Toast.makeText(MyApp.publicApp, "passwords don't match", Toast.LENGTH_SHORT).show()
            false
        }
    }

}