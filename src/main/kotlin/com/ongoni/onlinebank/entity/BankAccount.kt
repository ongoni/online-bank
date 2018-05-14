package com.ongoni.onlinebank.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "bank_account")
data class BankAccount(
        @Id @GeneratedValue
        private val id: Long,
        val openDate: Date,
        @ManyToOne
        val user: User,
        var balance: Double
)