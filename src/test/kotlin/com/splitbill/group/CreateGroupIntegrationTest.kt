package com.splitbill.group

import com.splitbill.auth.entity.TokenResponseEntity
import com.splitbill.common.IntegrationTest
import com.splitbill.common.util.MongoUtils
import com.splitbill.group.repository.group.GroupRepository
import com.splitbill.profile.model.ProfileModel
import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpHeaders

// TODO this integration test
@IntegrationTest
class CreateGroupIntegrationTest(
    @Autowired val groupRepository: GroupRepository,
    @Autowired val restTemplate: TestRestTemplate
) {

    @BeforeAll
    fun clean() {
        MongoUtils.cleanDB()
    }

    fun `Created group`() {
        val email = "mail@mail.com"
        val request = ProfileModel(email, "pass")
        restTemplate.postForEntity<ProfileModel>("/profile/", request).body!!
        val loginToken = restTemplate.postForEntity<TokenResponseEntity>("/login/", mapOf("email" to email, "password" to "pass")).body!!


        val headers = HttpHeaders()
        headers.setBearerAuth(loginToken.token)
        //val entity = HttpEntity("body", headers)


        restTemplate.postForEntity<Any>("/group/", headers, mapOf("name" to "familia")).body!!

    }
}