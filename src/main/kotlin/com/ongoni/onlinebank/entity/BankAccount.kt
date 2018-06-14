package com.ongoni.onlinebank.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "bank_account")
data class BankAccount(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private var id: Long = 0,

        val uuid: String = UUID.randomUUID().toString(),

        val openDate: Date = Date(),

        @ManyToOne
        val user: User = User(),

        var balance: Double = 0.0
)
