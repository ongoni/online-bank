package com.ongoni.onlinebank.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping("/", "/home")
    @PreAuthorize("hasAuthority('USER')")
    fun home() : String {
        return "user/home"
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    fun admin(): String {
        return "admin/admin"
    }

}