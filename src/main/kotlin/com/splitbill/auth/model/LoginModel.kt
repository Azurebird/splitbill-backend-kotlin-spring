package com.splitbill.auth.model

import com.splitbill.model.Model
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("login") data class LoginModel(
        val userId: String,
        val email: String,
        val passwordHash: String): Model() {

    @Id
    var loginId: String? = null
}