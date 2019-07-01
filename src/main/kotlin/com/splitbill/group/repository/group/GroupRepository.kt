package com.splitbill.group.repository.group

import com.splitbill.group.model.GroupModel
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface GroupRepository : CrudRepository<GroupModel, String> {

    fun findAllByProfileIdsEquals(profileId: String): List<GroupModel>
}