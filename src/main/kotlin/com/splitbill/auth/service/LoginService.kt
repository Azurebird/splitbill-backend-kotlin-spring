package com.splitbill.auth.service

import org.springframework.security.core.userdetails.UserDetails

interface LoginService {

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
    fun findByToken(toString: String): UserDetails?

    /**
     * TODO
     *
     * @param username
     * @param password
     * @return
     */
    fun login(username: String, password: String): String?
}