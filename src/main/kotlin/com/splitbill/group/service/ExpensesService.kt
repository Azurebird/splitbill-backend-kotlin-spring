package com.splitbill.group.service

import com.splitbill.group.model.Expense
import java.math.BigDecimal

interface ExpensesService {

    fun addExpense(profileId: String, groupId: String, detail: String, amount: BigDecimal)

    fun getExpenses(profileId: String, groupId: String): List<Expense>
}