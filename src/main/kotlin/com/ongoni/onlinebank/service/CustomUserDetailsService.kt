package com.ongoni.onlinebank.service

import com.ongoni.onlinebank.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User

@Service
class CustomUserDetailsService : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(login: String): UserDetails {
        val user = userRepository.findOneByLogin(login)


        return User.withDefaultPasswordEncoder()
                .username(user.login)
                .password(user.password)
                .roles("USER")
                .build()
    }
}