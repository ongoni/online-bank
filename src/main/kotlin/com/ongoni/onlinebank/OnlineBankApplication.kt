package com.ongoni.onlinebank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OnlineBankApplication {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            runApplication<OnlineBankApplication>(*args)
        }
    }

}
