package com.splitbill.group.service

import com.splitbill.common.exception.GroupNotFoundException
import com.splitbill.group.model.GroupModel
import com.splitbill.group.repository.group.GroupRepository
import com.splitbill.profile.service.ProfileService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Group service in charge o
 *
 * @property groupRepository
 */
@Service
class GroupServiceImpl(
    val profileService: ProfileService,
    val groupRepository: GroupRepository
) : GroupService {

    /**
     * Creates a new group given the name and the user who created it
     *
     * @param name The name of the group
     * @param profileId The profile id of the user who created it
     * @return
     */
    override fun create(name: String, profileId: String): GroupModel {
        return groupRepository.save(GroupModel(name, profileId))
    }

    /**
     * List the groups on which this profileId is part of
     *
     * @param profileId The profileId
     * @return
     */
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
    @Transactional
    override fun settleDown(groupId: String, profileId: String): GroupModel {
        val group = groupRepository.findByIdOrNull(groupId) ?: throw GroupNotFoundException()
        group.settleDown()
        return groupRepository.save(group)
    }

    @Transactional
    override fun addProfile(groupId: String, email: String) {
        val group = groupRepository.findByIdOrNull(groupId) ?: throw GroupNotFoundException()
        val profile = profileService.getProfileByEmail(email)
        val wasAdded = group.addNewProfile(profile.profileId!!)
        if (wasAdded) groupRepository.save(group)
    }
}