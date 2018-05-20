package com.ongoni.onlinebank.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "transaction")
data class Transaction(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private var id: Long = 0,
        @ManyToOne
        val from: BankAccount = BankAccount(),
        @ManyToOne
        val to: BankAccount = BankAccount(),
        val amount: Double = 0.0,
        val date: Date = Date(),
        @Column(name = "is_rolled_back")
        var isRolledBack: Boolean = false
)