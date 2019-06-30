package com.splitbill.group.controller

import com.splitbill.auth.model.LoginModel
import com.splitbill.group.dto.CreateExpenseRequest
import com.splitbill.group.model.Expense
import com.splitbill.group.model.GroupModel
import com.splitbill.group.service.ExpensesService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/expense")
class ExpensesController(
    val expensesService: ExpensesService
) {

    @PostMapping("/")
    fun createExpense(
        @RequestBody expense: CreateExpenseRequest,
        auth: Authentication
    ): ResponseEntity<Any> {
        val login = auth.principal as LoginModel
        expensesService.addExpense(login.profileId, expense.groupId, expense.detail, expense.amount)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/")
    fun getExpenses(@RequestParam groupId: String, auth: Authentication): ResponseEntity<GroupModel> {
        val login = auth.principal as LoginModel
        return ResponseEntity.ok(expensesService.getExpenses(login.profileId, groupId))
    }
}