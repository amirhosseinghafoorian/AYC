package com.a.remotemodule

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class HomeRemote
@Inject constructor(
    private val auth: FirebaseAuth,
    private val rootReference: DatabaseReference
) {

    fun currentUser(): FirebaseUser? = auth.currentUser

    fun logout() = auth.signOut()

    fun getUserInfo(): DatabaseReference {
        return rootReference
            .child("Users")
            .child(auth.currentUser?.uid.toString())
            .child("Name")
    }

    fun getUsersList(): DatabaseReference {
        return rootReference
            .child("Users")
    }

}