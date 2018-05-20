package com.ongoni.onlinebank.entity

import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private var id: Long = 0,
        var login: String = "",
        var password: String = "",
        @Column(name = "first_name", nullable = true)
        var firstName: String = "",
        @Column(name = "last_name", nullable = true)
        var lastName: String = "",
        var active: Boolean = true,
        @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
        @CollectionTable(name = "role", joinColumns = [(JoinColumn(name = "user_id"))])
        @Enumerated(EnumType.STRING)
        val roles: MutableSet<Role> = mutableSetOf()
)