package com.splitbill.group.repository

import com.splitbill.group.model.Expense
import com.splitbill.group.model.GroupModel
import com.splitbill.group.repository.projection.ExpensesOnly
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface ExpensesRepository: CrudRepository<GroupModel, String> {

    fun findOneByProfileIdsAndGroupId(profileId: String, groupId: String): ExpensesOnly
}
