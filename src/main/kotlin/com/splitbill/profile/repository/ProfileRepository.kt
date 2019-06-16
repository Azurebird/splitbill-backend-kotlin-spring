package com.splitbill.profile.repository

import com.splitbill.profile.model.ProfileModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface ProfileRepository: CrudRepository<ProfileModel, String>
