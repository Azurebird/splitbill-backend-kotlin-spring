package com.splitbill.profile.service

import com.splitbill.auth.service.login.LoginService
import com.splitbill.common.exception.ProfileNotFoundException
import com.splitbill.common.exception.UserAlreadyExistsException
import com.splitbill.profile.model.ProfileModel
import com.splitbill.profile.repository.ProfileRepository
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(
    val loginService: LoginService,
    val profileRepository: ProfileRepository
) : ProfileService {

    /**
     * Had a hard time here deciding if I should check if the profile exists before, or let the database throw an error
     *
     * @param email
     * @param password
     * @return
     */
    override fun createProfile(email: String, password: String): ProfileModel {
        try {
            val newProfile = profileRepository.save(ProfileModel(email, password))
            loginService.createLogin(newProfile.profileId!!, email, password)
            newProfile.deletePassword()
            return newProfile
        } catch (e: DuplicateKeyException) {
            throw UserAlreadyExistsException()
        }
    }

    /**
     * Gets a profile given its id, if it not exists a NotFoundException is thrown
     *
     * @param profileId The profileId
     * @return
     */
    override fun getProfileByEmail(email: String): ProfileModel {
        return profileRepository.findByEmail(email) ?: throw ProfileNotFoundException()
    }
}