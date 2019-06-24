package com.splitbill.profile.controller

import com.google.gson.Gson
import com.splitbill.common.config.security.TokenAuthenticationProvider
import com.splitbill.profile.model.ProfileModel
import com.splitbill.profile.service.ProfileService
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(ProfileController::class)
class ProfileControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var profileService: ProfileService

    @TestConfiguration
    class ProfileControllerTestConfig {

        @Bean
        fun profileService() = mockk<ProfileService>()

        @Bean
        fun tokenAuthenticationProvider() = mockk<TokenAuthenticationProvider>(relaxed = true)
    }

    @Test
    fun `Create profile in profile service was successfully called`() {

        val mail = "mail@mail.com"
        val body = ProfileModel(mail, "pass")

        every { profileService.createProfile(body.email, body.password) } returns ProfileModel(mail, "")

        val result = Gson().fromJson(
            mockMvc.perform(
                post("/profile/")
                    .contentType(APPLICATION_JSON_UTF8)
                    .content(Gson().toJson(body)))
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString, ProfileModel::class.java)

        assertEquals(mail, result.email)
        assertEquals("", result.password)
        verify { profileService.createProfile(mail, body.password) }
        confirmVerified(profileService)
    }
}