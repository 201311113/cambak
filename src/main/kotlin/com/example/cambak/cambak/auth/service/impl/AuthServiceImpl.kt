package com.example.cambak.cambak.auth.service.impl

import com.example.cambak.cambak.auth.model.AuthDto
import com.example.cambak.cambak.auth.service.AuthService
import com.example.cambak.cambak.common.jwt.JwtUtils
import com.example.cambak.cambak.common.util.EMAIL_NOT_FOUND
import com.example.cambak.cambak.common.util.OK
import com.example.cambak.cambak.common.util.PASSWORD_NOT_MATCHED
import com.example.cambak.cambak.common.util.RepositoryProvider
import com.example.cambak.database.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    var repo: RepositoryProvider,
    var jwtUtils: JwtUtils,
    var encodePassword: BCryptPasswordEncoder
): AuthService {

    override fun signUp(req: AuthDto.SignUpReq): AuthDto.SignUpRes {
        //중복 검사 parameter달라질 수 있음
        if(repo.userRepository.existsByEmail(req.email)) return AuthDto.SignUpRes(EMAIL_NOT_FOUND)

        //회원 DB 등록
        repo.userRepository.save(
            User(
                email = req.email,
                password = encodePassword.encode(req.password),
                nickname = req.nickname,
                mobileNo = req.mobileNo,
                isVendor = req.isVendor
            )
        )

        return AuthDto.SignUpRes(OK)
    }

    override fun signIn(req: AuthDto.SignInReq): AuthDto.SignInRes {
        //validation
        //  email
        val user = repo.userRepository.findByEmail(req.email)
            ?: return AuthDto.SignInRes(EMAIL_NOT_FOUND)
        //  password
        if(!encodePassword.matches(req.password,user.password)) return AuthDto.SignInRes(PASSWORD_NOT_MATCHED)
        val accessToken: String = jwtUtils.createAccessToken(req.email, arrayOf("ROLE_USER"))

        return AuthDto.SignInRes(
            OK,
            accessToken = accessToken
        )

    }
}