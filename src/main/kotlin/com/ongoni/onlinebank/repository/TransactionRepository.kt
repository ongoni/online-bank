package com.ongoni.onlinebank.repository

import com.ongoni.onlinebank.entity.BankAccount
import com.ongoni.onlinebank.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long> {

    fun findAllByFrom(accounts: List<BankAccount>): List<Transaction>

    fun findAllByTo(accounts: List<BankAccount>): List<Transaction>

}