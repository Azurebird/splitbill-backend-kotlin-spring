package com.splitbill.profile.model

import com.splitbill.common.model.Model
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("profile") data class ProfileModel(
    @Indexed(unique = true) val email: String,
    @Transient var password: String = ""
) : Model() {

    @Id var profileId: String? = null

    fun deletePassword() {
        this.password = ""
    }
}