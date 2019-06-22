package com.splitbill.group.repository

import com.splitbill.group.model.GroupModel
import org.springframework.data.mongodb.repository.MongoRepository

interface GroupRepositoryMongoDB : GroupRepository, MongoRepository<GroupModel, String>