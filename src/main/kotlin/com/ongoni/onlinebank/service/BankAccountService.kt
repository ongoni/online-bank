package com.ongoni.onlinebank.service

import com.ongoni.onlinebank.entity.BankAccount
import com.ongoni.onlinebank.entity.User
import com.ongoni.onlinebank.repository.BankAccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BankAccountService {
    @Autowired
    private lateinit var bankAccountRepository: BankAccountRepository

    fun save(bankAccount: BankAccount) {
        bankAccountRepository.save(bankAccount)
    }

    fun findById(id: Long): Optional<BankAccount> {
        return bankAccountRepository.findById(id)
    }

    fun findAll(): List<BankAccount> {
        return bankAccountRepository.findAll()
    }

    fun findBankAccountsBy(user: User): List<BankAccount> {
        return bankAccountRepository.findByUser(user)
    }

}