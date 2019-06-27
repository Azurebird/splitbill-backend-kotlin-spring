package com.splitbill.group.service

import java.math.BigDecimal

interface ExpensesService {

    fun addExpense(profileId: String, groupId: String, detail: String, amount: BigDecimal)
}