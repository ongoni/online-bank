package com.ongoni.onlinebank.repository

import com.ongoni.onlinebank.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long>