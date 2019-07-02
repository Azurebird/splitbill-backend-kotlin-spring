package com.splitbill.group.service

import com.splitbill.group.model.GroupModel

interface GroupService {

    fun create(name: String, profileId: String): GroupModel

    fun list(profileId: String): List<GroupModel>

    fun settleDown(groupId: String, profileId: String): GroupModel

    fun addProfile(groupId: String, email: String)
}
