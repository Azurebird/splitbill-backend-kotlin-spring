package com.splitbill.group.repository

import com.splitbill.group.model.GroupModel
import org.springframework.data.mongodb.repository.Query

interface ExpensesRepositoryMongoDB : ExpensesRepository {

    @Query(fields="{name: 1, expenses: 1}")
    override fun findOneByProfileIdsAndGroupId(profileId: String, groupId: String): GroupModel
}