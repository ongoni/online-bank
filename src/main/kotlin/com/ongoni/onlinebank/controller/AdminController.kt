package com.ongoni.onlinebank.controller

import com.ongoni.onlinebank.entity.BankAccount
import com.ongoni.onlinebank.entity.Transaction
import com.ongoni.onlinebank.entity.User
import com.ongoni.onlinebank.service.BankAccountService
import com.ongoni.onlinebank.service.TransactionService
import com.ongoni.onlinebank.service.UserService
import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
class AdminController {
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var transactionService: TransactionService
    @Autowired
    private lateinit var bankAccountService: BankAccountService

    @GetMapping("/users")
    fun getUsers() : List<User> {
        return userService.findAll()
    }

    @GetMapping("/accounts")
    fun getUsersAccounts() : List<BankAccount> {
        return bankAccountService.findAll()
    }

    @GetMapping("/transactions")
    fun getUsersTransactions() : List<Transaction> {
        return transactionService.findAll()
    }

    fun rollbackTransaction() {

    }

    fun createUser() {

    }

    fun editUserDetails(id: Long, newDetails: Map<String, String>) {

    }

}