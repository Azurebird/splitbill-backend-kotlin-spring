package com.splitbill.auth.service.login

interface LoginService {

    fun createLogin(userId: String, email: String, password: String)
}