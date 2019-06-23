package com.splitbill.group.model

import com.splitbill.common.model.Model
import org.bson.types.Decimal128
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("group")
data class GroupModel (
    val name: String
) : Model() {

    @Id
    var groupId: String? = null
    var profileIds = ArrayList<String>()
    var expenseGroup = ExpenseGroup()

    constructor(name: String, profileId: String) : this(name) {
        profileIds.add(profileId)
    }

    fun settleDown() {
        expenseGroup = ExpenseGroup()
    }

    /**
     * An expense group can only exists in a group and can only be created inside of it
     */
    inner class ExpenseGroup {
        var expenses = ArrayList<Expense>()

        fun addExpense(profileId: String, detail: String, amount: Decimal128) {
            expenses.add(Expense(profileId, detail, amount))
        }

        inner class Expense(
            var profileId: String,
            var detail: String,
            var amount: Decimal128
        )
    }
}