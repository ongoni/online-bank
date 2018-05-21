package com.ongoni.onlinebank.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var dataSource: DataSource

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/signup", "/login").permitAll().anyRequest().authenticated()
                .antMatchers("/admin", "/h2_console/**").hasRole("ADMIN").anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/home").permitAll()
                .and()
                .logout().permitAll()
    }
}