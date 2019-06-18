package com.splitbill.auth.service.authentication

import org.springframework.security.core.userdetails.UserDetails

interface AuthenticationService {

    /**
     * TODO
     *
     * @param toString
     * @return
     */
    fun findByToken(token: String): UserDetails?

    /**
     * TODO
     *
     * @param username
     * @param password
     * @return
     */
    fun login(email: String, password: String): String?
}