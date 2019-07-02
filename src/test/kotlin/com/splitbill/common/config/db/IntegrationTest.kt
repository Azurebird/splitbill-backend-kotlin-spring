package com.splitbill.common.config.db

import com.splitbill.AppApplication
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [AppApplication::class, MongoConfig::class])
annotation class IntegrationTest