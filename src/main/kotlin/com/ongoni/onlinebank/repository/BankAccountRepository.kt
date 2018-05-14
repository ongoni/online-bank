package com.ongoni.onlinebank.repository

import com.ongoni.onlinebank.entity.BankAccount
import org.springframework.data.jpa.repository.JpaRepository

interface BankAccountRepository : JpaRepository<BankAccount, Long>