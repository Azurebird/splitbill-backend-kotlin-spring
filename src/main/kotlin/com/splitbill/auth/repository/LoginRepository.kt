package com.splitbill.auth.repository

import com.splitbill.auth.model.LoginModel
import org.springframework.data.mongodb.repository.MongoRepository

interface LoginRepository : MongoRepository<LoginModel, String> {

    /**
     * Finds a user by it's email
     *
     * @param email
     */
    fun findByEmail(email: String?): LoginModel?
}