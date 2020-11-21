package com.a.ayc.user.domain

import com.a.ayc.home.data.HomeRemote
import com.a.ayc.user.data.UserRemote
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
    private val userRemote: UserRemote,
    private val homeRemote: HomeRemote
) {

    fun setUserInfo(name: String , username : String) = userRemote.setUserInfo(name , username)

}