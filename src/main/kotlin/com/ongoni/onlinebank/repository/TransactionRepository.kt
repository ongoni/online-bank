package com.ongoni.onlinebank.repository

import com.ongoni.onlinebank.entity.Transaction
import com.ongoni.onlinebank.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Int> {

    fun findByFrom(from: User): Iterable<Transaction>

    fun findByTo(to: User): Iterable<Transaction>

}