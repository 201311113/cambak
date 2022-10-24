package com.example.cambak.cambak.domains.user.controller

import com.example.cambak.cambak.common.util.ServiceProvider
import com.example.cambak.cambak.domains.user.service.UserDevService
import com.example.cambak.database.entity.UserTemp
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dev/user")
@Api(tags = ["[Dev User] 개발자 유저 데이터 관리 관련 API"])
class UserDevController {
    @Autowired
    lateinit var userDevService: UserDevService

    @GetMapping("")
    fun migration(

    ):List<UserTemp>{
        return userDevService.migration()
    }
}