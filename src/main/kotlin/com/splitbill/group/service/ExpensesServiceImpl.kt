package com.splitbill.group.service

import com.splitbill.common.exception.GroupNotFoundException
import com.splitbill.group.model.Expense
import com.splitbill.group.model.GroupModel
import com.splitbill.group.repository.ExpensesRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class ExpensesServiceImpl(
    val expensesRepository: ExpensesRepository
) : ExpensesService {

    @Transactional
    override fun addExpense(profileId: String, groupId: String, detail: String, amount: BigDecimal) {
        val group = expensesRepository.findByIdOrNull(groupId) ?: throw GroupNotFoundException()
        group.addExpense(profileId, detail, amount)
        expensesRepository.save(group)
    }

    override fun getExpenses(profileId: String, groupId: String): GroupModel {
        return expensesRepository.findOneByProfileIdsAndGroupId(profileId, groupId)
    }
}