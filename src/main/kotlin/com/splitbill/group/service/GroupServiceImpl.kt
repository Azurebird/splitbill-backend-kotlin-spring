package com.splitbill.group.service

import com.splitbill.group.model.GroupModel
import com.splitbill.group.repository.GroupRepository
import org.springframework.stereotype.Service

@Service
class GroupServiceImpl(
    val groupRepository: GroupRepository
) : GroupService {

    override fun create(name: String, profileId: String): GroupModel {
        return groupRepository.save(GroupModel(name, profileId))
    }
}