package com.ongoni.onlinebank.repository

import com.ongoni.onlinebank.entity.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository : JpaRepository<Admin, Int> {

}