package com.ongoni.onlinebank.repository

import com.ongoni.onlinebank.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findOneByLogin(login: String): User

}