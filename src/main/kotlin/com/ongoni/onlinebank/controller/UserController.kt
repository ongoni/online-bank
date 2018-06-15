package com.ongoni.onlinebank.controller

import com.ongoni.onlinebank.entity.Transaction
import com.ongoni.onlinebank.entity.User
import com.ongoni.onlinebank.service.BankAccountService
import com.ongoni.onlinebank.service.TransactionService
import com.ongoni.onlinebank.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
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

        return "user/transactions"
    }

    @GetMapping("/accounts")
    fun getAccounts(model: Model): String {
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findByLogin(auth.name)

        model.addAttribute("accounts", bankAccountService.findBankAccountsBy(user))

        return "user/accounts"
    }

    @GetMapping("/edit")
    fun edit(model: Model): String {
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findByLogin(auth.name)

        model.addAttribute("user", user)
        model.addAttribute("roles", user.roles.map { x -> x.name }.toSet())

        return "user/edit"
    }

    @PostMapping("/save")
    fun save(
            @RequestParam username: String,
            @RequestParam form: Map<String, String>,
            @RequestParam("userId") user: User
    ): String {
        val auth = SecurityContextHolder.getContext().authentication

        user.login = username
        user.firstName = form["firstName"].toString()
        user.lastName = form["lastName"].toString()

        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(username, user.password, auth.authorities)

        userService.save(user)

        return "redirect:/home"
    }

    @GetMapping("/transaction")
    fun transaction(model: Model): String {
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findByLogin(auth.name)

        model.addAttribute("userAccounts", bankAccountService.findBankAccountsBy(user))
        model.addAttribute("accounts", bankAccountService.findAll().filter { x -> !x.user.deleted && x.user != user})

        return "user/transaction"
    }

    @PostMapping("transaction/proceed")
    fun proceedTransaction(
            @RequestParam form: Map<String, String>,
            model: Model
    ): String {
        val from = bankAccountService.findById(form["from"]!!.toLong()).get()
        val to = bankAccountService.findById(form["to"]!!.toLong()).get()
        val amount = form["amount"]!!.toDouble()

        if (from.balance > amount) {
            val transaction = Transaction(
                    from = from,
                    to = to,
                    amount = amount
            )

            transaction.from.balance -= transaction.amount
            transaction.to.balance += transaction.amount

            transactionService.save(transaction)
        }

        return "redirect:/user/transactions"
    }

}