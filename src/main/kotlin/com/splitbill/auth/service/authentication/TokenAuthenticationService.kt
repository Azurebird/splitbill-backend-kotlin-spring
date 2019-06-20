package com.splitbill.auth.service.authentication

import com.splitbill.auth.repository.LoginRepository
import com.splitbill.auth.service.token.TokenService
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException
import java.lang.Exception
import java.lang.IllegalArgumentException

@Service
class TokenAuthenticationService(
    val tokenService: TokenService,
    val passwordEncoder: PasswordEncoder,
    val loginRepository: LoginRepository
): AuthenticationService {

    override fun authenticate(email: String, password: String): String? {
        val loginModel = loginRepository.findByEmail(email)
        if(loginModel == null || !passwordEncoder.matches(password, loginModel.password)) {
            throw IllegalArgumentException("Invalid login and/or password")
        }
        return tokenService.permanentToken(mapOf("email" to email))
    }

    override fun findByToken(token: String): UserDetails? {
        val claims = tokenService.verify(token)
        val email = claims["email"]
        return loginRepository.findByEmail(email)
    }
}