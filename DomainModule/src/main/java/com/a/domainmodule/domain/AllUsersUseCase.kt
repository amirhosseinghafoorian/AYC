package com.a.domainmodule.domain

import com.a.remotemodule.HomeRemote
import javax.inject.Inject

class AllUsersUseCase @Inject constructor(
    private val homeRemote: HomeRemote
) {

    fun getUsersList() = homeRemote.getUsersList()

    fun usernameFromUid(uid : String) = homeRemote.usernameFromUid(uid)

    fun userDirect(uid : String) = homeRemote.userDirect(uid)

}