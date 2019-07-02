package com.splitbill.group.model

import com.splitbill.common.model.Model
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document("group")
data class GroupModel(
    val name: String
) : Model() {

    @Id
    var groupId: String? = null
    private var profileIds = HashSet<String>()
    var expenses = ArrayList<Expense>()
    var profilesTotal = HashMap<String, BigDecimal>()

    /**
     * Constructor to be used when creating a group for the first time
     */
    constructor(name: String, profileId: String) : this(name) {
        addNewProfile(profileId)
    }

    /**
     * Settles down the expenses by replacing the existing array with a new one
     */
    fun settleDown() {
        expenses = ArrayList()
    }

    /**
     * Adds a new expense the the expenses history and updates the total for that profile
     *
     * @param profileId The profile who added the new expense
     * @param detail The detail of the expense explaining it
     * @param amount The amount of the expense
     */
    fun addExpense(profileId: String, detail: String, amount: BigDecimal) {
        profilesTotal[profileId] = amount.add(profilesTotal[profileId])
        expenses.add(Expense(profileId, detail, amount))
    }

    /**
     * Adds a new profile to this group to share expenses with the other profiles
     *
     * @param profileId The profile id of the new profile
     * @return true in case the profile was actually added
     */
    fun addNewProfile(profileId: String): Boolean {
        profilesTotal[profileId] = BigDecimal.ZERO
        return profileIds.add(profileId)
    }
}

/**
 * Class representing an expense made by a user
 *
 * @property profileId The user identifier
 * @property detail The detail of the expense
 * @property amount The amount of the expense
 */
class Expense(
    var profileId: String,
    var detail: String,
    var amount: BigDecimal
)