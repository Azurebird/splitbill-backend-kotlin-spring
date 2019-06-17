package com.splitbill.auth.service

import com.splitbill.auth.model.LoginModel
import com.splitbill.auth.repository.LoginRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl(
        val passwordEncoder: PasswordEncoder,
        val loginRepository: LoginRepository
): LoginService {

    override fun login(username: String, password: String): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findByToken(toString: String): UserDetails? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createLogin(userId: String, email: String, password: String) {
        val passwordHash = passwordEncoder.encode(password)
        loginRepository.save(LoginModel(userId, email, passwordHash))
    }
}