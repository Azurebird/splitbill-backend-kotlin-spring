package com.splitbill.auth.service

import com.splitbill.auth.repository.LoginRepository
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl(val loginRepository: LoginRepository): LoginService {

    override fun createLogin(userId: String, email: String, password: String) {
    }
}