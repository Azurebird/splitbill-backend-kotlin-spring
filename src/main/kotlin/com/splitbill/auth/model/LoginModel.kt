package com.splitbill.auth.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class LoginModel(
        val userId: String,
        val email: String,
        val passwordHash: String
) {

    @Id
    val loginId: String? = null
}