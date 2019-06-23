package com.splitbill.group.service

import com.splitbill.group.model.GroupModel
import com.splitbill.group.repository.GroupRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GroupServiceImpl(
    val groupRepository: GroupRepository
) : GroupService {

    /**
     * Creates a new group given the name and the user who created it
     *
     * @param name The name of the group
     * @param profileId The profile id of the user who created it
     * @return
     */
    @Transactional
    override fun create(name: String, profileId: String): GroupModel {
        return groupRepository.save(GroupModel(name, profileId))
    }

    /**
     * List the groups on which this profileId is part of
     *
     * @param profileId The profileId
     * @return
     */
    @Transactional(readOnly = true)
    override fun list(profileId: String): List<GroupModel> {
        return groupRepository.findAllByProfileIdsEquals(profileId)
    }

    /**
     * TODO
     *
     * @param groupId
     * @param profileId
     * @return
     */
    override fun settleUp(groupId: String, profileId: String): GroupModel.ExpenseGroup {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}