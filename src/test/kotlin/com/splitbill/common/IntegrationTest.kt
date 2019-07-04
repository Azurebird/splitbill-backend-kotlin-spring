package com.splitbill.common

import com.splitbill.AppApplication
import com.splitbill.common.config.db.MongoConfig
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [AppApplication::class, MongoConfig::class])
annotation class IntegrationTest