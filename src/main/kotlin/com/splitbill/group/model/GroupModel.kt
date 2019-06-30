package com.splitbill.group.model

import com.splitbill.common.model.Model
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document("group")
data class GroupModel (
    val name: String
) : Model() {

    @Id
    var groupId: String? = null
    private var profileIds = ArrayList<String>()
    var expenses = ArrayList<Expense>()

    constructor(name: String, profileId: String) : this(name) {
        profileIds.add(profileId)
    }

    fun settleDown() {
        expenses = ArrayList()
    }

    fun addExpense(profileId: String, detail: String, amount: BigDecimal) {
        expenses.add(Expense(profileId, detail, amount))
    }
}

class Expense(
    var profileId: String,
    var detail: String,
    var amount: BigDecimal
)