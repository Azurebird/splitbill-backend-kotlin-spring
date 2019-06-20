package com.splitbill.auth.service.authentication

import org.springframework.security.core.userdetails.UserDetails

interface AuthenticationService {

    fun findByToken(token: String): UserDetails?

    fun authenticate(email: String, password: String): String?
}