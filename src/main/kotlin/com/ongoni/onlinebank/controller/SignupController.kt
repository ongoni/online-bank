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
    fun signUp() : String {
        return "signup"
    }

    @PostMapping("/signup")
    fun registerUser(user: User, bindingResult: BindingResult, model: Model) : String {
//        if (userRepository.findByLogin(user.login) != null) {
//            model.addAttribute("message", "user already exists!")
//        }

        user.active = true
        user.roles.add(Role.USER)
        userRepository.save(user)

        return "redirect:/login"
    }

}