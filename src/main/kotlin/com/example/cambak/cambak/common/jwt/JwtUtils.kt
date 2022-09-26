package com.example.cambak.cambak.common.jwt

import com.example.cambak.cambak.common.util.CommonUtils
import com.example.cambak.cambak.common.util.RepositoryProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import java.time.Duration
import java.util.*
import javax.servlet.http.HttpServletRequest
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import kotlin.jvm.Throws

@Component
class JwtUtils (
    @Value("\${jwt.secret.key}") val secretKey:String,
    @Value("\${jwt.refresh.key}") val refreshKey:String,
    private val repo: RepositoryProvider

){


    //TODO: 토큰유효 시간 설정 레거시 정하기

    fun createAccessToken(
        userId: String,
        roles: Array<String>,
    ):String{
        val accessTokenValidTime: Long = 30*60*10000L
        return generate(userId, roles, accessTokenValidTime, secretKey)
    }

    fun createRefreshToken(
        username: String,
        roles: Array<String>,
    ):String{
        val refreshTokenValidTime: Long = 30*60*2000L
        return generate(username, roles, refreshTokenValidTime, refreshKey)
    }

    fun getTokenInHeader(req: HttpServletRequest): String?{
        return req.getHeader("Authorization")
    }

    @Throws(ExpiredJwtException::class)
    fun validateToken(token: String): Boolean{
        try{
            val decodedToken = decode(token,secretKey)

            return decodedToken.expiresAt.after(CommonUtils.setNowAsDateTime())
        }catch (e: ExpiredJwtException){
            return false
        }
    }

    private fun generate(
        userId: String,
        roles: Array<String>,
        validatedTime: Long,
        signature: String
    ): String{
        val now = CommonUtils.setNowAsDateTime()
        val validity = Date(getExpiration(validatedTime))

        return JWT.create()
            .withSubject(userId)
            .withIssuer("turtlz")
            .withExpiresAt(Date(System.currentTimeMillis() + validatedTime))
            .withArrayClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .sign(Algorithm.HMAC512(signature.toByteArray()))
    }

    private fun getExpiration(validatedTime: Long): Long = Date().toInstant()
        .plus(Duration.ofMillis(validatedTime))
        .toEpochMilli()

    private fun decode(
        token: String,
        signature: String,
    ): DecodedJWT {
        return JWT.require(Algorithm.HMAC512(signature.toByteArray()))
            .withIssuer("turtlz")
            .build()
            .verify(token.replace("Bearer ", ""))
    }

    fun getAuthentication(token: String) : Authentication{
        val jwt = decode(token, secretKey)
        val userId = jwt.subject
        val authorities = getRoles(jwt)

        //db와 비교
        //TODO: user 설계 후 기
//        if(!repo.userRepository.existsByUserId(userId)) throw Exception("토큰이 유효하지 않습니다.")


        return UsernamePasswordAuthenticationToken(userId, null, authorities)
    }

    fun getRoles(decodedJWT: DecodedJWT) = decodedJWT.getClaim("roles").asList(String::class.java)
        .map{ SimpleGrantedAuthority(it) }

}