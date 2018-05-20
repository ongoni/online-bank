//package com.ongoni.onlinebank.config
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.provisioning.InMemoryUserDetailsManager
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import javax.sql.DataSource
//import org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder
//import org.apache.tomcat.jni.User.username
//import org.springframework.security.core.userdetails.User
//import org.springframework.security.core.userdetails.UserDetails
//
//
//
//@Configuration
//@EnableWebSecurity
//class WebSecurityConfig : WebSecurityConfigurerAdapter() {
//    @Autowired
//    lateinit var dataSource: DataSource
//
//    @Throws(Exception::class)
//    override fun configure(http: HttpSecurity) {
//        http
//                .authorizeRequests()
//                    .antMatchers("/", "/home", "/signup", "/login").permitAll()
//                    .anyRequest().authenticated()
//                    .antMatchers("/admin").hasRole("ADMIN")
//                .and()
//                    .formLogin().loginPage("/login")
//                    .defaultSuccessUrl("/home").permitAll()
//                .and()
//                    .logout().logoutUrl("/login?logout").permitAll()
//    }
//
//    @Bean
//    public override fun userDetailsService(): UserDetailsService {
//        val user = User.withDefaultPasswordEncoder()
//                .username("q")
//                .password("1")
//                .roles("ADMIN")
//                .build()
//
//        return InMemoryUserDetailsManager(user)
//    }
//
////    override fun configure(auth: AuthenticationManagerBuilder?) {
////        auth
////                ?.jdbcAuthentication()
////                ?.dataSource(dataSource)
////                ?.passwordEncoder(BCryptPasswordEncoder())
////                ?.usersByUsernameQuery("select username, password, active from user where username=?")
////                ?.authoritiesByUsernameQuery("select u.username, ur.roles from user u inner join role r on u.id = r.user_id where u.username=?")
////    }
//}