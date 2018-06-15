package com.ongoni.onlinebank.controller

import com.ongoni.onlinebank.entity.Role
import com.ongoni.onlinebank.entity.User
import com.ongoni.onlinebank.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class SignupController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @GetMapping("/signup")
    fun signUp(): String {
        return "user/signup"
    }

    @PostMapping("/signup")
    fun registerUser(user: User, bindingResult: BindingResult, model: Model): String {
        user.active = true
        user.roles.add(Role.USER)

        try {
            userRepository.save(user)
        } catch (ex: Exception) {
            return "redirect:/signup?error"
        }

        return "redirect:/login"
    }

}