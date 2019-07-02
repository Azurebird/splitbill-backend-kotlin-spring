package com.splitbill.profile

import com.splitbill.auth.repository.LoginRepository
import com.splitbill.common.config.db.IntegrationTest
import com.splitbill.profile.model.ProfileModel
import com.splitbill.profile.repository.ProfileRepository
import io.jsonwebtoken.lang.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus


@IntegrationTest
class CreateProfileIntegrationTest(
    @Autowired val loginRepository: LoginRepository,
    @Autowired val profileRepository: ProfileRepository,
    @Autowired val restTemplate: TestRestTemplate
) {

    @BeforeAll
    fun clean() {
        loginRepository.deleteAll()
        profileRepository.deleteAll()
    }

    @Test
    fun `Profile creation done successfully` () {
        val email = "mail@mail.com"
        val request = ProfileModel(email, "pass")

        val entity = restTemplate.postForEntity<ProfileModel>("/profile/", request)

        Assertions.assertEquals(HttpStatus.OK, entity.statusCode)

        val createdLogin = loginRepository.findByEmail(entity.body?.email)
        val createdProfile = profileRepository.findByEmail(entity.body!!.email)
        Assertions.assertEquals(email, createdLogin?.email)
        Assertions.assertEquals(email, createdProfile?.email)
        Assert.notNull(createdLogin?.passwordHash)
    }
}