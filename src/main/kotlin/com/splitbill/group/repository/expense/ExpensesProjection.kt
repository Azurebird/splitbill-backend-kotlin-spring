package com.splitbill.group.repository.expense

import com.splitbill.group.model.Expense

interface ExpensesOnly {

    fun getName(): String

    fun getExpenses(): List<Expense>
}

interface NameOnly {

    fun getName(): String
}