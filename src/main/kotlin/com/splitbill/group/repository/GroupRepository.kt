package com.splitbill.group.repository

import com.splitbill.group.model.GroupModel
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface GroupRepository: CrudRepository<GroupModel, String> {

    fun findAllByProfileIdsEquals(profileId: String): List<GroupModel>
}