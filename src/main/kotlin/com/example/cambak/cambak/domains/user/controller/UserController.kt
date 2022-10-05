package com.example.cambak.cambak.domains.user.controller

import com.example.cambak.cambak.common.util.ServiceProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    lateinit var serviceProvider: ServiceProvider

    //user 데이터 수정 컨트롤러 - auth controller 는 로그인만 담당
}