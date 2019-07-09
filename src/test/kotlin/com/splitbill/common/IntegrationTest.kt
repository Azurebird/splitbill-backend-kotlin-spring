package com.splitbill.common

import com.splitbill.AppApplication
import com.splitbill.common.config.db.MongoConfig
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [AppApplication::class, MongoConfig::class])
annotation class IntegrationTest