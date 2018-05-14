package com.ongoni.onlinebank.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {

    @RequestMapping("/index")
    fun index() : String {
        return "index"
    }

}