package com.example.cambak.cambak.domains.campingcar.controller

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.domains.campingcar.model.CampingCarConfigDto
import com.example.cambak.cambak.domains.campingcar.service.CampingCarConfigService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dev/camping-car-config")
@Api(tags = ["[DEV - CampingCar Config] 캠핑카 부가 서비스 관련 API"])
class CampingCarConfigController {

    @Autowired
    lateinit var campingCarConfigService: CampingCarConfigService

    @ApiOperation(
        value = "캠핑카 보유 시설 및 부가기능 종류 추가",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PostMapping("/add")
    fun addConfig(
        @RequestBody req: CampingCarConfigDto.AddConfigReq
    ): Response{
        println("[[[[[[[[[[[[[[[[[[--------- 부가기능 추가 ---------]]]]]]]]]]]]]]]]]")
        return campingCarConfigService.addConfig(req)
    }
    @PostMapping("/add/type")
    fun addConfigType(
        @RequestBody req: CampingCarConfigDto.AddConfigTypeReq
    ): Response{
        return campingCarConfigService.addConfigType(req)
    }
}