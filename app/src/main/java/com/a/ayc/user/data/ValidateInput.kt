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
        return true
    }
}