package com.example.cambak.cambak.domains.auth.service.impl

import com.example.cambak.cambak.domains.auth.model.AuthDto
import com.example.cambak.cambak.domains.auth.service.AuthService
import com.example.cambak.cambak.common.jwt.JwtUtils
import com.example.cambak.cambak.common.util.*
import com.example.cambak.database.entity.SignInType
import com.example.cambak.database.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class AuthServiceImpl(
    var repo: RepositoryProvider,
    var jwtUtils: JwtUtils,
    var encodePassword: BCryptPasswordEncoder
): AuthService {

    override fun signUpEmail(req: AuthDto.SignUpReq): AuthDto.SignUpRes {
        //validate
        if(repo.userRepository.existsByEmailAndSignInTypeAndActive(req.email,SignInType.EMAIL)) return AuthDto.SignUpRes(EMAIL_ALREADY_EXISTED)

        //회원 DB 등록
        repo.userRepository.save(
            User(
                userId = req.email,
                email = req.email,
                password = encodePassword.encode(req.password),
                nickname = req.nickname,
                mobileNo = req.mobileNo,
                isVendor = req.isVendor,
                signInType = SignInType.EMAIL
            )
        )

        return AuthDto.SignUpRes(OK)
    }

    override fun signIn(req: AuthDto.SignInReq): AuthDto.SignInRes {
        //validation
        var user: User
        if(req.signInType == SignInType.EMAIL)
            user = emailValidate(req.userId, req.password)
        else
            //TODO: 아래 메소드 user하도록 구현
//            snsValidate(req.SignInId, req.snsToken)
            return AuthDto.SignInRes(UNKNOWN_ERROR)

        //sns는 snsid email은 email로 토큰 generate
        val accessToken: String = jwtUtils.createAccessToken(req.userId, arrayOf("ROLE_USER"))

        return AuthDto.SignInRes(
            OK,
            accessToken = accessToken
        )

    }

    @Throws(BadRequestException::class)
    private fun emailValidate(
        email: String,
        password: String?
    ):User{
        if(password == null) throw BadRequestException(PASSWORD_NOT_FOUND)
        val user = repo.userRepository.findByEmailAndActive(email)
            ?: throw BadRequestException(EMAIL_NOT_FOUND)
        if(!encodePassword.matches(password,user.password)) throw BadRequestException(PASSWORD_NOT_MATCHED)
        return user
    }

    @Throws(BadRequestException::class)
    private fun snsValidate(
        snsId: String,
        snsToken: String?,
    ){
        //TODO: 추후 구현 sns token 검증, 첫 로그인 생성 등,...
        if(snsToken == null) throw BadRequestException(SNS_TOKEN_NOT_FOUND)
        createSnsAccount()
    }

    @Throws(BadRequestException::class)
    private fun createSnsAccount(){
        //TODO: sns 첫 로그인시 create account

    }
}