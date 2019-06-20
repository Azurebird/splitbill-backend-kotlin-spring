package com.splitbill.auth.service.authentication

import com.splitbill.auth.repository.LoginRepository
import com.splitbill.auth.service.token.TokenService
import com.splitbill.common.exception.InvalidCredentialsException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class TokenAuthenticationService(
    val tokenService: TokenService,
    val passwordEncoder: PasswordEncoder,
    val loginRepository: LoginRepository
): AuthenticationService {

    override fun authenticate(email: String, password: String): String? {
        val loginModel = loginRepository.findByEmail(email)
        if(loginModel == null || !passwordEncoder.matches(password, loginModel.password)) {
            throw InvalidCredentialsException()
        }
        return tokenService.permanentToken(mapOf("email" to email))
    }

    override fun findByToken(token: String): UserDetails? {
        val claims = tokenService.verify(token)
        val email = claims["email"]
        return loginRepository.findByEmail(email)
    }
}