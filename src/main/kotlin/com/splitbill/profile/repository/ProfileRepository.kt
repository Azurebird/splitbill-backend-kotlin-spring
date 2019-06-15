package com.splitbill.profile.repository

import com.splitbill.profile.model.ProfileModel
import org.springframework.data.mongodb.repository.MongoRepository

interface ProfileRepository: MongoRepository<ProfileModel, String>
