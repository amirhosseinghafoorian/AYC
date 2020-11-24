package com.a.ayc.model

import com.a.ayc.general.MessageType

data class MessageModel (
    var id : String ,
    var text : String ,
    var type : MessageType ,
    var time : String? = null
)