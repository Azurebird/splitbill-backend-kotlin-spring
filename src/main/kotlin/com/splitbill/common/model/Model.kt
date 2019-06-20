package com.splitbill.common.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*

/**
 * This class defines common properties and methods used by every model
 */
abstract class Model {
    @CreatedDate var createdAt: Date? = null
    @LastModifiedDate var lastUpdate: Date? = null
}