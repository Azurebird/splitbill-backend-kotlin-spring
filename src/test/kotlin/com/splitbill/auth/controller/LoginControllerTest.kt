package com.splitbill.auth.controller

import com.google.gson.Gson
import com.splitbill.auth.entity.TokenResponseEntity
import com.splitbill.auth.service.authentication.AuthenticationService
import com.splitbill.common.config.security.TokenAuthenticationProvider
import com.splitbill.profile.model.ProfileModel
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(LoginController::class)
class LoginControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var authenticationService: AuthenticationService

    @TestConfiguration
    class ProfileControllerTestConfig {

        @Bean
        fun authenticationService() = mockk<AuthenticationService>()

        @Bean
        fun tokenAuthenticationProvider() = mockk<TokenAuthenticationProvider>(relaxed = true)
    }
    @Test
    fun `(SUCCESS) Login rest method made`() {
        val email = "mail@mail.com"
        val password = "password"
        val jwtToken = "thisisanexampletokenfortheunittest"

        every { authenticationService.authenticate(email, password) } returns jwtToken

        val result = Gson().fromJson(
            mockMvc.perform(
                MockMvcRequestBuilders.post("/login/")
                    .param("email", email)
                    .param("password", password))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
                .response
                .contentAsString, TokenResponseEntity::class.java)

        Assertions.assertEquals(jwtToken, result.token)
        verify { authenticationService.authenticate(email, password) }
        confirmVerified(authenticationService)
    }

    @Test
    fun `(ERROR) email is required at login`() {
        val password = "password"

        mockMvc.perform(
            MockMvcRequestBuilders.post("/login/")
                .param("password", password))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)

        confirmVerified(authenticationService)
    }

    @Test
    fun `(ERROR) password is required at login`() {
        val email = "mail@mail.com"

        mockMvc.perform(
            MockMvcRequestBuilders.post("/login/")
                .param("email", email))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)

        confirmVerified(authenticationService)
    }

}
