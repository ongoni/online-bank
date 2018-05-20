package com.ongoni.onlinebank.service

import com.ongoni.onlinebank.entity.BankAccount
import com.ongoni.onlinebank.entity.Transaction
import com.ongoni.onlinebank.entity.User
import com.ongoni.onlinebank.repository.BankAccountRepository
import com.ongoni.onlinebank.repository.TransactionRepository
import com.ongoni.onlinebank.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var transactionRepository: TransactionRepository
    @Autowired
    private lateinit var bankAccountRepository: BankAccountRepository

    fun findTransactionsBy(user: User): List<Transaction> {
        return transactionRepository.findByFrom(user)
                .plus(transactionRepository.findByTo(user))
    }

    fun findBankAccountsBy(user: User): List<BankAccount> {
        return bankAccountRepository.findByUser(user)
    }

    fun findAll(): MutableList<User> {
        return userRepository.findAll()
    }
}