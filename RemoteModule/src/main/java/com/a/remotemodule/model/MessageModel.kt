package com.a.remotemodule.model

data class MessageModel (
    var id : String ,
    var text : String ,
    var type : MessageType ,
    var time : String? = null
)