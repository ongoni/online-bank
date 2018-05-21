package com.ongoni.onlinebank.repository

import com.ongoni.onlinebank.entity.BankAccount
import com.ongoni.onlinebank.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface BankAccountRepository : JpaRepository<BankAccount, Long> {

    fun findByUser(user: User): List<BankAccount>

}