package com.splitbill.auth.repository

import com.splitbill.auth.model.LoginModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface LoginRepository: MongoRepository<LoginModel, String> {
}