package com.splitbill.security.config

import com.splitbill.auth.service.LoginService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class TokenAuthenticationProvider(
        val loginService: LoginService
): AbstractUserDetailsAuthenticationProvider() {

    override fun retrieveUser(username: String?, authentication: UsernamePasswordAuthenticationToken?): UserDetails {
        val token = authentication?.credentials
        return loginService.findByToken(token.toString()) ?: throw UsernameNotFoundException("Cannot find user with authentication token=${token}")
    }

    override fun additionalAuthenticationChecks(userDetails: UserDetails?, authentication: UsernamePasswordAuthenticationToken?) {
        // Not required
    }
}