package com.splitbill.profile.service

import com.splitbill.auth.service.AuthenticationService
import com.splitbill.profile.model.ProfileModel
import com.splitbill.profile.repository.ProfileRepository
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(
        val loginService: AuthenticationService,
        val profileRepository: ProfileRepository): ProfileService {

    /**
     * TODO
     *
     * @param email
     * @param password
     * @return
     */
    override fun createProfile(email: String, password: String): ProfileModel {
        val newProfile = profileRepository.save(ProfileModel(email, password))
        loginService.createLogin(newProfile.profileId!!, email, password)
        newProfile.deletePassword()
        return newProfile
    }
}