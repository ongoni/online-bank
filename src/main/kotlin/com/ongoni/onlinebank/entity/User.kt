package com.ongoni.onlinebank.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @Column(unique = true)
        var login: String = "",

        @get:JvmName("getPassword_")
        var password: String = "",

        @Column(name = "first_name", nullable = true)
        var firstName: String = "",

        @Column(name = "last_name", nullable = true)
        var lastName: String = "",

        var active: Boolean = true,

        @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
        @CollectionTable(name = "role", joinColumns = [(JoinColumn(name = "user_id"))])
        @Enumerated(EnumType.STRING)
        val roles: MutableSet<Role> = mutableSetOf(),

        var deleted: Boolean = false
) : UserDetails {

    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles
    }

    override fun isEnabled(): Boolean {
        return active
    }

    override fun getUsername(): String {
        return login
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    fun getFullName(): String{
        return "$firstName $lastName"
    }

}
