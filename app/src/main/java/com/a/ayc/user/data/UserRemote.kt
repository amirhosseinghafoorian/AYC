package com.a.ayc.user.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class UserRemote
@Inject constructor(
    private val auth: FirebaseAuth,
    private val rootReference: DatabaseReference
) {

    fun signUp(email: String, password: String): Task<AuthResult>? {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun login(email: String, password: String): Task<AuthResult>? {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun setUserInfo(name: String) {
        rootReference
            .child("Users")
            .child(auth.currentUser?.uid.toString())
            .child("Name").setValue(name)
    }

}
