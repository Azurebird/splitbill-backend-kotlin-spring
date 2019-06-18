package com.splitbill.profile.service

import com.splitbill.auth.service.authentication.AuthenticationService
import com.splitbill.auth.service.login.LoginService
import com.splitbill.profile.model.ProfileModel
import com.splitbill.profile.repository.ProfileRepository
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(
    val loginService: LoginService,
    val profileRepository: ProfileRepository): ProfileService {

    override fun createProfile(email: String, password: String): ProfileModel {
        val newProfile = profileRepository.save(ProfileModel(email, password))
        loginService.createLogin(newProfile.profileId!!, email, password)
        newProfile.deletePassword()
        return newProfile
    }
}