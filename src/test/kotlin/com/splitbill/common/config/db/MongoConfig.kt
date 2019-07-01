package com.splitbill.common.config.db

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {

    override fun mongoClient(): MongoClient {
        return MongoClients.create("mongodb://localhost:27017/?replicaSet=rs0")
    }

    override fun getDatabaseName(): String {
        return "test"
    }

    /**
     * Transaction manager, enables @Transactional annotation
     */
    @Bean
    fun transactionManager(dbFactory: MongoDbFactory): MongoTransactionManager {
        return MongoTransactionManager(dbFactory)
    }
}