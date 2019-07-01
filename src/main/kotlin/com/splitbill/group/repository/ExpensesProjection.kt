package com.splitbill.group.repository

import com.splitbill.group.model.Expense

interface ExpensesOnly {

    fun getName(): String

    fun getExpenses(): List<Expense>
}