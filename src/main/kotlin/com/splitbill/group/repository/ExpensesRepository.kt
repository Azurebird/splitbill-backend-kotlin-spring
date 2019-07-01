package com.splitbill.group.repository

import com.splitbill.group.model.GroupModel
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface ExpensesRepository: CrudRepository<GroupModel, String> {

    fun findOneByProfileIdsAndGroupId(profileId: String, groupId: String): ExpensesOnly
}
