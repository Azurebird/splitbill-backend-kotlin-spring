package com.splitbill.auth.controller

import com.splitbill.auth.entity.TokenResponseEntity
import com.splitbill.auth.service.authentication.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class LoginController(
        val authenticationService: AuthenticationService
) {

    @PostMapping("/")
    fun login(
            @RequestParam("email") username: String,
            @RequestParam("password") password: String
    ): ResponseEntity<TokenResponseEntity> {
        val tokenResponseEntity = TokenResponseEntity(authenticationService.authenticate(username, password))
        return ResponseEntity(tokenResponseEntity, HttpStatus.OK)
    }
}