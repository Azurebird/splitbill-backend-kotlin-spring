package com.splitbill.profile.repository

import com.splitbill.profile.model.ProfileModel
import org.springframework.data.mongodb.repository.MongoRepository

interface ProfileRepositoryMongoDB : ProfileRepository, MongoRepository<ProfileModel, String>
