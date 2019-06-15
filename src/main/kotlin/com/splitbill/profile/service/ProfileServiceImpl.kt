package com.splitbill.profile.service

import com.splitbill.profile.model.ProfileModel
import com.splitbill.profile.repository.ProfileRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileServiceImpl(val profileRepository: ProfileRepository): ProfileService {

    override fun createProfile(email: String, password: String?): ProfileModel {
        val newProfile = ProfileModel(email, password)
        newProfile.deletePassword()
        return profileRepository.save(newProfile)
    }
}