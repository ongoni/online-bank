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
        val ongoni = User(login = "ongoni", password = "qwe", firstName = "Alex", active = true, roles = mutableSetOf(Role.USER, Role.ADMIN))
        val leeroy = User(login = "leroy", password = "jenkins22", firstName = "Leeroy", lastName = "Jenkins", active = true, roles = mutableSetOf(Role.USER))
        val jaraxxus = User(login = "satan", password = "legion4eva", firstName = "Jaraxxus", lastName = "Eredar Lord", active = true, roles = mutableSetOf(Role.USER))
        val leeroyAcc = BankAccount(openDate = Date(), user = leeroy)
        val jaraxxusAcc = BankAccount(openDate = Date(), user = jaraxxus)
        val transaction1 = Transaction(from = leeroyAcc, to = jaraxxusAcc, amount = 1000.0, date = Date(), isRolledBack = false)
        val transaction2 = Transaction(from = jaraxxusAcc, to = leeroyAcc, amount = 1000.0, date = Date(), isRolledBack = false)

        userRepository.saveAndFlush(ongoni)
        userRepository.saveAndFlush(leeroy)
        userRepository.saveAndFlush(jaraxxus)
        bankAccountRepository.saveAndFlush(leeroyAcc)
        bankAccountRepository.saveAndFlush(jaraxxusAcc)
        transactionRepository.saveAndFlush(transaction1)
        transactionRepository.saveAndFlush(transaction2)
    }
}