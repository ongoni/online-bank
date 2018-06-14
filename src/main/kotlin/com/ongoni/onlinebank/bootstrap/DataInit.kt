package com.ongoni.onlinebank.bootstrap

import com.ongoni.onlinebank.entity.BankAccount
import com.ongoni.onlinebank.entity.Role
import com.ongoni.onlinebank.entity.Transaction
import com.ongoni.onlinebank.entity.User
import com.ongoni.onlinebank.repository.BankAccountRepository
import com.ongoni.onlinebank.repository.TransactionRepository
import com.ongoni.onlinebank.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class DataInit : ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var bankAccountRepository: BankAccountRepository
    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    override fun onApplicationEvent(p0: ContextRefreshedEvent) {
        init()
    }

    fun init() {
        val ongoni = User(login = "ongoni", password = "qwe", firstName = "Alex", roles = mutableSetOf(Role.USER, Role.ADMIN))
        val leeroy = User(login = "leroy", password = "jenkins22", firstName = "Leeroy", lastName = "Jenkins", roles = mutableSetOf(Role.USER))
        val jaraxxus = User(login = "satan", password = "legion4eva", firstName = "Jaraxxus", lastName = "Eredar Lord", roles = mutableSetOf(Role.USER))
        val leeroyAcc = BankAccount(user = leeroy, balance = 1000.0)
        val jaraxxusAcc = BankAccount(user = jaraxxus, balance = 1000.0)
        val ongoniAcc = BankAccount(user = ongoni, balance = 1000.0)
        val transaction1 = Transaction(from = leeroyAcc, to = jaraxxusAcc, amount = 1000.0)
        val transaction2 = Transaction(from = jaraxxusAcc, to = leeroyAcc, amount = 1000.0)
        val transaction3 = Transaction(from = ongoniAcc, to = leeroyAcc, amount = 1000.0, isRolledBack = true)

        userRepository.saveAndFlush(ongoni)
        userRepository.saveAndFlush(leeroy)
        userRepository.saveAndFlush(jaraxxus)
        bankAccountRepository.saveAndFlush(leeroyAcc)
        bankAccountRepository.saveAndFlush(jaraxxusAcc)
        bankAccountRepository.saveAndFlush(ongoniAcc)
        transactionRepository.saveAndFlush(transaction1)
        transactionRepository.saveAndFlush(transaction2)
        transactionRepository.saveAndFlush(transaction3)
    }
}