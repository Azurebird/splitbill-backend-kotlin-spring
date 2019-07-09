package com.splitbill.group

import com.splitbill.auth.entity.TokenResponseEntity
import com.splitbill.common.IntegrationTest
import com.splitbill.common.util.MongoUtils
import com.splitbill.group.repository.group.GroupRepository
import com.splitbill.profile.model.ProfileModel
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap


// TODO this integration test
@IntegrationTest
class CreateGroupIntegrationTest(
    @Autowired val groupRepository: GroupRepository,
    @Autowired val restTemplate: TestRestTemplate
) {

    val email = "mail@mail.com"
    val password = "pass"

    @BeforeAll
    fun clean() {
        MongoUtils.cleanDB()
    }

    @Test
    fun `Created group`() {

        val loginToken = doLogin(createProfile())

        val headers = HttpHeaders()
        headers.setBearerAuth(loginToken.token)
        val map = LinkedMultiValueMap<String, String>()
        map.add("name", "familia")
        val request = HttpEntity(map, headers)
        restTemplate.postForEntity<Any>("/group/", request)
    }

    private fun createProfile() : ProfileModel {
        return restTemplate.postForEntity<ProfileModel>("/profile/", ProfileModel(email, "pass")).body!!
    }

    private fun doLogin(profile: ProfileModel) : TokenResponseEntity {
        val map = LinkedMultiValueMap<String, String>()
        map.add("email", profile.email)
        map.add("password", password)

        val request = HttpEntity<MultiValueMap<String, String>>(map, HttpHeaders())
        return restTemplate.postForEntity<TokenResponseEntity>("/login/", request).body!!
    }
}