package com.example.cambak.cambak.common.jwt

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint (
): AuthenticationEntryPoint{

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        e: AuthenticationException
    ) {

        if(request.getAttribute("error").equals("401")) {
            response.status = HttpStatus.UNAUTHORIZED.value()
        }

    }
}