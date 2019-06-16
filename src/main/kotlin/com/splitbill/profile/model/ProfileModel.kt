package com.splitbill.profile.model

import com.splitbill.model.Model
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document

@Document("profile") data class ProfileModel (
        val email: String,
        @Transient var password: String = ""): Model() {

    @Id var profileId: String? = null

    fun deletePassword() {
        this.password = ""
    }
}