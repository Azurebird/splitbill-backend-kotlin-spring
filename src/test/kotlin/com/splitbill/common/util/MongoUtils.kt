package com.splitbill.common.util

import com.mongodb.client.MongoClients
import com.splitbill.auth.model.LoginModel
import com.splitbill.group.model.GroupModel
import com.splitbill.profile.model.ProfileModel
import org.springframework.data.mongodb.core.MongoTemplate

object MongoUtils {
    private val mongoTemplate = MongoTemplate(MongoClients.create("mongodb://localhost:27017/"), "test")
    private val collectionsNames = arrayOf(ProfileModel::class.java, LoginModel::class.java, GroupModel::class.java)

    fun cleanDB() {
        collectionsNames.forEach { e -> mongoTemplate.dropCollection(e) }
    }
}