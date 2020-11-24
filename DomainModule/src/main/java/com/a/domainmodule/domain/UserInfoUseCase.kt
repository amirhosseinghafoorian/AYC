package com.a.domainmodule.domain

import com.a.remotemodule.UserRemote
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
    private val userRemote: UserRemote
) {

    fun setUserInfo(name: String, username: String) = userRemote.setUserInfo(name, username)

}