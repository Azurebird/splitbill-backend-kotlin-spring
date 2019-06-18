package com.splitbill.auth.service

import com.splitbill.auth.model.LoginModel
import com.splitbill.auth.repository.LoginRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class TokenAuthenticationService(
        val tokenService: TokenService,
        val passwordEncoder: PasswordEncoder,
        val loginRepository: LoginRepository
): AuthenticationService {

    override fun login(email: String, password: String): String? {
        val loginModel = loginRepository.findByEmail(email)
        if(loginModel == null || !passwordEncoder.matches(password, loginModel.password)) {
            TODO("throw exception")
        }
        return tokenService.permanentToken(mapOf("email" to email))
    }

    override fun findByToken(token: String): UserDetails? {
        val claims = tokenService.verify(token)
        val email = claims["email"]
        return loginRepository.findByEmail(email)
    }

    // TODO("Should separate this method in other service")
    override fun createLogin(userId: String, email: String, password: String) {
        val passwordHash = passwordEncoder.encode(password)
        loginRepository.save(LoginModel(userId, email, passwordHash))
    }
}