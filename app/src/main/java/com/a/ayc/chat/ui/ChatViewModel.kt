package com.a.ayc.chat.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a.remotemodule.model.MessageModel
import com.a.domainmodule.domain.AllUsersUseCase
import com.a.domainmodule.domain.ChatUseCase
import com.a.domainmodule.domain.SignUpUseCase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChatViewModel @ViewModelInject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val chatUseCase: ChatUseCase,
    private val allUsersUseCase: AllUsersUseCase,

) : ViewModel() {

    var receiverUsername = MutableLiveData<String>()
    var isInDirect = MutableLiveData<Boolean>()
    var chatMessages = MutableLiveData<MutableList<String>>()

    init {
        chatMessages.value = mutableListOf()
    }

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

    fun isUserInDirect(cUid: String, uid: String) {
        allUsersUseCase.userDirect(cUid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var result = false
                    dataSnapshot.children.forEach {
                        if (it.key.toString() == uid) result = true
                    }
                    isInDirect.postValue(result)
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
    }

    fun chatIdDecide(receiverId: String) =
        allUsersUseCase.chatIdDecide(currentUser()?.uid.toString(), receiverId)

    fun createChatRoom(name: String) = chatUseCase.createChatRoom(name)

    fun putChatInDirect(base: String, target: String) =
        allUsersUseCase.putChatInDirect(base, target)

    fun openChat(chatId: String) {
        val chat = chatUseCase.getChatRoom(chatId)
        chat.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.i("chat_test", snapshot.child("text").value.toString())
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // if message edit added
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // if message delete added
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    fun sendMessage(message : MessageModel , chatId: String) = chatUseCase.sendMessage(message , chatId)

}