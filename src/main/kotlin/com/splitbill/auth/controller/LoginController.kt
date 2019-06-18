package com.splitbill.auth.controller

import com.splitbill.auth.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class LoginController(
        val loginService: AuthenticationService
) {

    @PostMapping("/")
    fun login(
            @RequestParam("email") username: String,
            @RequestParam("password") password: String
    ): String {
        return loginService.login(username, password) ?: throw RuntimeException("invalid login and/or password")
    }
}