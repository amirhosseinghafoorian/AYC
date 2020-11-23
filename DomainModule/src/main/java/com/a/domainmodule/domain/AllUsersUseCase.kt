package com.a.domainmodule.domain

import com.a.remotemodule.HomeRemote
import javax.inject.Inject

class AllUsersUseCase @Inject constructor(
    private val homeRemote: HomeRemote
) {

    fun getUsersList() = homeRemote.getUsersList()

}