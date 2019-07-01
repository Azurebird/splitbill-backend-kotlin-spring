package com.splitbill.profile.service

import com.splitbill.profile.model.ProfileModel

interface ProfileService {

    fun createProfile(email: String, password: String): ProfileModel

    fun getProfileByEmail(email: String): ProfileModel
}