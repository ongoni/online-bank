package com.ongoni.onlinebank.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class MainController {

    @GetMapping("/", "/home")
    fun home() : String {
        return "home"
    }

    @GetMapping("/hello")
    fun hello() : String {
        return "hello"
    }

}