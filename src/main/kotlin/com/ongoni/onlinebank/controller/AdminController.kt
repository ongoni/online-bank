package com.ongoni.onlinebank.controller

import com.ongoni.onlinebank.entity.BankAccount
import com.ongoni.onlinebank.entity.Transaction
import com.ongoni.onlinebank.entity.User
//import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
class AdminController {

    @GetMapping("/users")
    fun getUsers() : List<User> {
        return listOf()
    }

    @GetMapping("/users/accounts")
    fun getUsersAccounts() : List<BankAccount> {
        return listOf()
    }

    @GetMapping()
    fun getUsersTransactions() : List<Transaction> {
        return listOf()
    }

    fun rollbackTransaction() {

    }

    fun createUser() {

    }

    fun editUserDetails(id: Long, newDetails: Map<String, String>) {

    }

}