package com.splitbill.common.config.db

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {

    override fun mongoClient(): MongoClient {
        return MongoClients.create("mongodb://localhost:27017/?replicaSet=rs0")
    }

    override fun getDatabaseName(): String {
        return "test"
    }
}