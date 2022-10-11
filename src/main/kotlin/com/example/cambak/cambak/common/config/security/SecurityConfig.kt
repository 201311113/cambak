package com.example.cambak.cambak.common.config.security

import com.example.cambak.cambak.common.filter.JwtAuthenticationFilter
import com.example.cambak.cambak.common.jwt.JwtAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import kotlin.jvm.Throws

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
        ){
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.
                cors().and()
            .csrf().disable()
            .logout().disable()
            .formLogin().disable()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
                .antMatchers("/auth/token","/auth/register","/dev/**","/camping-car/configs",
                    "/swagger-ui/**","/swagger-resources/**","/v3/api-docs/**",)
                .permitAll()
                .antMatchers("/**")
                .authenticated()
            .and()
            .addFilterAfter(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun encodePassword(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }
}
