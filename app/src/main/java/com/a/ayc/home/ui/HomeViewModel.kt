package com.a.ayc.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a.ayc.model.UserModel
import com.a.domainmodule.domain.AllUsersUseCase
import com.a.domainmodule.domain.SignUpUseCase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HomeViewModel
@ViewModelInject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val allUsersUseCase: AllUsersUseCase
) : ViewModel() {

    var usersList = MutableLiveData<MutableList<UserModel>>()
    var chatsList = MutableLiveData<MutableList<UserModel>>()

    init {
        usersList.value = mutableListOf()
        chatsList.value = mutableListOf()
    }

    fun currentUser(): FirebaseUser? = signUpUseCase.currentUser()

    fun logout() = signUpUseCase.logout()

    fun getUsersList(text: String) {
        allUsersUseCase.getUsersList()
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val result = mutableListOf<UserModel>()
                    snapshot.children.forEach {
                        val value = it.child("Username").value.toString()
                        if (value.startsWith(text))
                            if (value != currentUser()?.email)
                                result.add(
                                    UserModel(
                                        it.key.toString(),
                                        value,
                                        it.child("Name").value.toString()
                                    )
                                )
                    }
                    usersList.postValue(result)
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

    fun getChatsList(cUid: String) {
        allUsersUseCase.userDirect(cUid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val result = mutableListOf<UserModel>()
                    dataSnapshot.children.forEach {
                        result.add(
                            UserModel(
                                it.key.toString(),
                                it.child("Username").value.toString(),
                                it.child("Name").value.toString()
                            )
                        )
                    }
                    chatsList.postValue(result)
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
    }
}