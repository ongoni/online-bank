package com.ongoni.onlinebank.controller

import com.ongoni.onlinebank.service.BankAccountService
import com.ongoni.onlinebank.service.TransactionService
import com.ongoni.onlinebank.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var transactionService: TransactionService
    @Autowired
    private lateinit var bankAccountService: BankAccountService

    @GetMapping("/transactions")
    fun getTransactions(model: Model): String {
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findByLogin(auth.name)

        model.addAttribute("transactions", transactionService.findTransactionsBy(user))

        return "transactions"
    }

    @GetMapping("/accounts")
    fun getAccounts(model: Model): String {
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findByLogin(auth.name)

        model.addAttribute("accounts", bankAccountService.findBankAccountsBy(user))

        return "accounts"
    }

    @GetMapping("/details")
    fun getDetails(model: Model): String {
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findByLogin(auth.name)

        model.addAttribute("user", user)

        return "userDetails"
    }

    @GetMapping("/edit")
    fun editDetails(model: Model): String {
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findByLogin(auth.name)

        model.addAttribute("user", user)

        return "userEdit"
    }

    @GetMapping("/transaction")
    fun transaction(model: Model): String {
        return "transaction"
    }

    @GetMapping("/transaction/new")
    fun makeTransaction(model: Model) {

    }

}