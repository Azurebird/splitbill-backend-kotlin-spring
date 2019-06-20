package com.splitbill.config.security

import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.RequestMatcher

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import javax.servlet.FilterChain


class TokenAuthenticationFilter (
        requiresAuthenticationRequestMatcher: RequestMatcher
) : AbstractAuthenticationProcessingFilter(requiresAuthenticationRequestMatcher) {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

        val param = request?.getHeader(HttpHeaders.AUTHORIZATION) ?: request?.getParameter("t")
        val bearerArray = param?.split(" ") ?: throw BadCredentialsException("Missing Authentication Token")
        val token = bearerArray[1]

        val auth = UsernamePasswordAuthenticationToken(token, token)
        return this.authenticationManager.authenticate(auth)
    }

    /**
     * In case of a successful authentication the next filter in the chain should be called
     */
    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        super.successfulAuthentication(request, response, chain, authResult)
        chain?.doFilter(request, response)
    }
}