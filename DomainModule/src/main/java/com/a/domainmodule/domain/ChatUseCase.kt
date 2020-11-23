package com.a.domainmodule.domain

import com.a.remotemodule.ChatRemote
import javax.inject.Inject

class ChatUseCase @Inject constructor(
    private val chatRemote: ChatRemote
) {

    fun createChatRoom(name: String) = chatRemote.createChatRoom(name)

    fun getChatRoom(chatId: String) = chatRemote.getChatRoom(chatId)

}