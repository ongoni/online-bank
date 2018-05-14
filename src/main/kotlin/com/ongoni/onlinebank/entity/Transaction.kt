package com.ongoni.onlinebank.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "transaction")
data class Transaction(
        @Id @GeneratedValue
        private val id: Long,
        @ManyToOne
        val from: BankAccount,
        @ManyToOne
        val to: BankAccount,
        val amount: Long,
        val date: Date,
        var isRolledBack: Boolean
)