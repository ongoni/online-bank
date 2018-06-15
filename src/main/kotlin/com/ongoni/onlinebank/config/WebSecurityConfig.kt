package com.ongoni.onlinebank.config

import com.ongoni.onlinebank.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.password.NoOpPasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailsService

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/signup", "/login").permitAll().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth
                ?.userDetailsService(customUserDetailsService)
                ?.passwordEncoder(NoOpPasswordEncoder.getInstance())
    }

}