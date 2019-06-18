package com.splitbill.auth.service

import org.springframework.security.core.userdetails.UserDetails

interface AuthenticationService {

    /**
     * TODO
     *
     * @param userId
     * @param email
     * @param password
     */
    fun createLogin(userId: String, email: String, password: String)

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