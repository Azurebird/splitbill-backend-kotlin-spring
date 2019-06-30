package com.splitbill.group.repository

import com.splitbill.group.model.GroupModel
import org.springframework.data.mongodb.repository.Query

interface GroupRepositoryMongoDB : GroupRepository {

    @Query(fields="{expenses : 0}")
    override fun findAllByProfileIdsEquals(profileId: String): List<GroupModel>
}