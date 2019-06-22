package com.splitbill.group.model

import com.splitbill.common.model.Model
import org.bson.types.Decimal128
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class GroupModel (
    val name: String,
    profileId: String
) : Model() {

    @Id
    var groupId: String? = null
    val profileIds = ArrayList<String>()
    var expenseGroup = ExpenseGroup()

    init {
        profileIds.add(profileId)
    }

    fun settleDown() {
        expenseGroup = ExpenseGroup()
    }

    inner class ExpenseGroup {
        val expenses = ArrayList<Expense>()

        fun addExpense(profileId: String, detail: String, amount: Decimal128) {
            expenses.add(Expense(profileId, detail, amount))
        }

        inner class Expense(
            val profileId: String,
            val detail: String,
            val amount: Decimal128
        )
    }
}