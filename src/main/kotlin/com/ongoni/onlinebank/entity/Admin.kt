package com.ongoni.onlinebank.entity

import javax.persistence.*

@Entity
@Table(name = "admin")
data class Admin(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private val id: Long,
        var login: String,
        var password: String,
        @Column(name = "first_name")
        var firstName: String,
        @Column(name = "last_name")
        var lastName: String
)