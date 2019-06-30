package com.splitbill.group.service

import com.splitbill.common.exception.GroupNotFoundException
import com.splitbill.group.model.Expense
import com.splitbill.group.repository.GroupRepositoryMongoDB
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class ExpensesServiceImpl(
    val groupRepository: GroupRepositoryMongoDB
) : ExpensesService {

    @Transactional
    override fun addExpense(profileId: String, groupId: String, detail: String, amount: BigDecimal) {
        val group = groupRepository.findByIdOrNull(groupId) ?: throw GroupNotFoundException()
        group.addExpense(profileId, detail, amount)
        groupRepository.save(group)
    }

    override fun getExpenses(profileId: String, groupId: String): List<Expense> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}