package com.splitbill.auth.service.login

import com.splitbill.auth.model.LoginModel
import com.splitbill.auth.repository.LoginRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl(
    val passwordEncoder: PasswordEncoder,
    val loginRepository: LoginRepository
) : LoginService {

    override fun createLogin(userId: String, email: String, password: String) {
        val passwordHash = passwordEncoder.encode(password)
        loginRepository.save(LoginModel(userId, email, passwordHash))
    }
}