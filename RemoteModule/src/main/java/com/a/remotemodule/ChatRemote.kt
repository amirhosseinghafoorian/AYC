package com.a.remotemodule

import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class ChatRemote @Inject constructor(
    private val rootReference: DatabaseReference
) {

    fun createChatRoom(name: String) {
        rootReference
            .child("ChatRooms")
            .child(name).setValue("")
    }

    fun getChatRoom(chatId: String): DatabaseReference {
        return rootReference
            .child("ChatRooms")
            .child(chatId)
    }
}