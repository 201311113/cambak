package com.example.cambak.cambak.common.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import kotlin.jvm.Throws

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.
                cors().and()
            .csrf().disable()
            .logout().disable()
            .formLogin().disable()
            .authorizeRequests()
                .antMatchers("/auth/token","/auth/register")
                .permitAll()
                .and()
            .authorizeRequests()
                .antMatchers("/mobile/test/**","/auth/delete")
                .authenticated()
                .and()
//            .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun encodePassword(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }
}
