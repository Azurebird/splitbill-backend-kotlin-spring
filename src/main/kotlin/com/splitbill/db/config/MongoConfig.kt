package com.splitbill.db.config

import com.mongodb.MongoClient
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = ["com.splitbill.*.repository"])
class MongoConfig: AbstractMongoConfiguration() {

    override fun mongoClient(): MongoClient {
        return MongoClient("127.0.0.1", 27017)
    }

    override fun getDatabaseName(): String {
        return "test"
    }

}