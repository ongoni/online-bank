package com.ongoni.onlinebank.controller

import com.ongoni.onlinebank.entity.User
import com.ongoni.onlinebank.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/transactions/{user}")
    fun getTransactions(@PathVariable user: User, model: Model): String {
        model.addAttribute("transactions", userService.findTransactionsBy(user))
        return "transactions"
    }

    @GetMapping("/accounts/{user}")
    fun getAccounts(@PathVariable user: User, model: Model): String {
        model.addAttribute("accounts", userService.findBankAccountsBy(user))
        return "accounts"
    }

    @GetMapping("/details/{user}")
    fun getDetails(@PathVariable user: User, model: Model): String {
        model.addAttribute("user", user)
        return "userDetails"
    }

    @GetMapping("/edit/{user}")
    fun editDetails(@PathVariable user: User, model: Model): String {
        model.addAttribute("user", user)
        return "userEdit"
    }

    @GetMapping("/transaction")
    fun makeTransaction(model: Model): String {
        return "makeTransaction"
    }

}