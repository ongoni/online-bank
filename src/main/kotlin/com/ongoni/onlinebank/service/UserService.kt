package com.ongoni.onlinebank.service

import com.ongoni.onlinebank.entity.User
import com.ongoni.onlinebank.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    fun findByLogin(login: String) : User {
        return userRepository.findOneByLogin(login)
    }
}