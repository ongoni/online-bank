package com.ongoni.onlinebank.controller

import com.ongoni.onlinebank.entity.Role
import com.ongoni.onlinebank.entity.Transaction
import com.ongoni.onlinebank.entity.User
import com.ongoni.onlinebank.service.BankAccountService
import com.ongoni.onlinebank.service.TransactionService
import com.ongoni.onlinebank.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
class AdminController {
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var transactionService: TransactionService
    @Autowired
    private lateinit var bankAccountService: BankAccountService

    @GetMapping("/admin")
    fun admin(): String {
        return "admin/admin"
    }

    @GetMapping("/users")
    fun getUsers(model: Model): String {
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findByLogin(auth.name)
        val users = userService.findAll().toMutableList()

        users.remove(user)

        model.addAttribute("users", users)

        return "admin/users"
    }

    @PostMapping("/user/new")
    fun createUser(model: Model): String {
        model.addAttribute("user", User())
        model.addAttribute("roles", setOf<String>())

        return "admin/newUser"
    }

    @PostMapping("/user/new/save")
    fun saveNewUser(
            @RequestParam username: String,
            @RequestParam form: Map<String, String>
    ): String {
        val user = User(
                login = username,
                firstName = form["firstName"].toString(),
                lastName = form["lastName"].toString(),
                password = form["password"].toString(),
                roles = mutableSetOf()
        )

        val roles = Role.values()
                .map { x -> x.name }
                .toSet()

        form.keys.forEach {
            if (roles.contains(it)) {
                user.roles.add(Role.valueOf(it))
            }
        }

        userService.save(user)

        return "redirect:/admin/users"
    }

    @GetMapping("/user/edit/{user}")
    fun editUser(@PathVariable user: User, model: Model): String {
        model.addAttribute("user", user)
        model.addAttribute("roles", user.roles.map { x -> x.name }.toSet())

        return "admin/userEdit"
    }

    @PostMapping("/user/save")
    fun saveUser(
            @RequestParam username: String,
            @RequestParam form: Map<String, String>,
            @RequestParam("userId") user: User
    ): String {
        user.login = username
        user.firstName = form["firstName"].toString()
        user.lastName = form["lastName"].toString()

        val roles = Role.values()
                .map { x -> x.name }
                .toSet()

        user.roles.clear()
        form.keys.forEach {
            if (roles.contains(it)) {
                user.roles.add(Role.valueOf(it))
            }
        }

        userService.save(user)

        return "redirect:/admin/users"
    }

    @GetMapping("/user/delete/{user}")
    fun deleteUser(@PathVariable user: User, model: Model): String {
        val userAccounts = bankAccountService.findBankAccountsBy(user)
        val transactions = transactionService.findTransactionsBy(user)

        user.deleted = true

        transactions.forEach {
            rollback(it)
        }

        userAccounts.forEach {
            it.balance = 0.0
            bankAccountService.save(it)
        }

        userService.save(user)

        return "redirect:/admin/users"
    }

    @GetMapping("/accounts")
    fun getUsersAccounts(model: Model): String {
        model.addAttribute("accounts", bankAccountService.findAll())

        return "admin/accounts"
    }

    @GetMapping("/transactions")
    fun getUsersTransactions(model: Model): String {
        model.addAttribute("transactions", transactionService.findAll())

        return "admin/transactions"
    }

    @GetMapping("/rollback/{transaction}")
    fun rollbackTransaction(@PathVariable transaction: Transaction, model: Model): String {
        rollback(transaction)

        return "redirect:/admin/transactions"
    }

    private fun rollback(transaction: Transaction) {
        if (!transaction.isRolledBack) {
            transaction.from.balance += transaction.amount
            transaction.to.balance -= transaction.amount
            transaction.isRolledBack = true

            transactionService.save(transaction)
        }
    }

}