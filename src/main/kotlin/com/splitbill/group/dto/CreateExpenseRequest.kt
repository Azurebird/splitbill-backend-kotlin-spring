package com.splitbill.group.dto

import java.math.BigDecimal

data class CreateExpenseRequest(
    val groupId: String,
    val detail: String,
    val amount: BigDecimal
)