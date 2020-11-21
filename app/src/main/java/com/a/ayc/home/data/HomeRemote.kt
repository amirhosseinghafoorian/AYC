package com.a.ayc.home.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class HomeRemote
@Inject constructor(private val auth: FirebaseAuth) {

    fun currentUser(): FirebaseUser? = auth.currentUser

    fun logout() = auth.signOut()

}