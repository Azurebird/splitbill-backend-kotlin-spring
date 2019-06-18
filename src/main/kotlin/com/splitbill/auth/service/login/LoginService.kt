package com.splitbill.auth.service.login

interface LoginService {

    /**
     * TODO
     *
     * @param userId
     * @param email
     * @param password
     */
    fun createLogin(userId: String, email: String, password: String)
}