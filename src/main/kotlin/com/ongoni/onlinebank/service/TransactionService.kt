package com.ongoni.onlinebank.service

import com.ongoni.onlinebank.entity.Transaction
import com.ongoni.onlinebank.entity.User
import com.ongoni.onlinebank.repository.BankAccountRepository
import com.ongoni.onlinebank.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService {
    @Autowired
    private lateinit var transactionRepository: TransactionRepository
    @Autowired
    private lateinit var bankAccountRepository: BankAccountRepository

    fun findAll(): List<Transaction> {
        return transactionRepository.findAll()
    }

    fun findTransactionsBy(user: User): List<Transaction> {
        val userAccounts = bankAccountRepository.findByUser(user)

        return transactionRepository
                .findAllByFrom(userAccounts)
                .plus(transactionRepository
                        .findAllByTo(userAccounts)
                )
    }

}