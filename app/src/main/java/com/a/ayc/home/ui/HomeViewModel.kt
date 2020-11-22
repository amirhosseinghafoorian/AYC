package com.a.ayc.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a.domainmodule.domain.SignUpUseCase
import com.a.domainmodule.domain.UserInfoUseCase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HomeViewModel
@ViewModelInject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val userInfoUseCase: UserInfoUseCase,
) : ViewModel() {

    val name = MutableLiveData<String>()

    fun currentUser(): FirebaseUser? = signUpUseCase.currentUser()

    fun logout() = signUpUseCase.logout()

    fun getUserInfo(){
        userInfoUseCase.getUserInfo()
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    name.postValue(dataSnapshot.value.toString())
                }
                override fun onCancelled(databaseError: DatabaseError) {}
            })
    }

}