package com.example.cambak.cambak.common.filter

import com.example.cambak.cambak.common.jwt.JwtUtils
import io.jsonwebtoken.JwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
class JwtAuthenticationFilter (

): OncePerRequestFilter(){

    @Autowired
    lateinit var jwtUtils: JwtUtils

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = jwtUtils.getTokenInHeader(request)
        try {
            if(token != null && jwtUtils.validateToken(token)){
                val auth: Authentication = jwtUtils.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = auth
                if (!auth.isAuthenticated) throw JwtException("토큰이 올바르지 않습니다")
            }
            else {
                request.setAttribute("error", "401")
            }
        }catch (e: Exception){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
        }
        filterChain.doFilter(request,response)
    }


}