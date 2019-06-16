package com.splitbill.profile.service

import com.splitbill.auth.service.LoginService
import com.splitbill.profile.model.ProfileModel
import com.splitbill.profile.repository.ProfileRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileServiceImpl(
        val loginService: LoginService,
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