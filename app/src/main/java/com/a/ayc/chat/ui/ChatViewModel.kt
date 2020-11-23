package com.a.ayc.chat.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a.domainmodule.domain.AllUsersUseCase
import com.a.domainmodule.domain.SignUpUseCase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChatViewModel @ViewModelInject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val allUsersUseCase: AllUsersUseCase
) : ViewModel() {

    val receiverUsername = MutableLiveData<String>()

    fun currentUser(): FirebaseUser? = signUpUseCase.currentUser()

    fun usernameFromUid(uid: String) {
        allUsersUseCase.usernameFromUid(uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    receiverUsername.postValue(dataSnapshot.value.toString())
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

    }

    fun isUserInDirect(id: String) {
        allUsersUseCase.userDirect(currentUser()?.uid.toString())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.children.forEach {

                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
    }
}