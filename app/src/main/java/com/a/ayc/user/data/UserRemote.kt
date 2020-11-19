package com.a.ayc.user.data

import android.widget.Toast
import com.a.ayc.MyApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserRemote {

    fun signUp(email : String , password : String) : FirebaseUser? {
        val mAuth = FirebaseAuth.getInstance()
        var user : FirebaseUser? = null
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        MyApp.publicApp,
                        "Signed Up Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    user = mAuth.currentUser
                } else {
                    Toast.makeText(
                        MyApp.publicApp,
                        "Sign Up Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        return user
    }

}