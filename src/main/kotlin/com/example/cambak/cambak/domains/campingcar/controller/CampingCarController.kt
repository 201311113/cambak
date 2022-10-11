package com.example.cambak.cambak.domains.campingcar.controller

import com.example.cambak.cambak.common.util.OK
import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.common.util.ServiceProvider
import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto
import com.mysql.cj.x.protobuf.Mysqlx.Ok
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/camping-car")
@Api(tags = ["[CampingCar] 캠핑카 관련 API"])
class CampingCarController {
    @Autowired
    lateinit var serviceProvider: ServiceProvider

    @ApiOperation(
        value = "캠핑카 등록",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PostMapping("/register")
    fun enroll(
        @RequestBody req: CampingCarDto.EnrollCampingCarReq
    ): CampingCarDto.EnrollCampingCarRes{

        println("[[[[[[[[[[[[[[[[[[--------- 캠핑카 등록 ---------]]]]]]]]]]]]]]]]]")
        return serviceProvider.campingcarService.enroll(req)
    }

    fun enrollImages(

    ):Response{
        return Response(OK)
    }

    @ApiOperation(
        value = "캠핑카 부가 시설 리스트업",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/configs")
    fun configListUp(): CampingCarDto.ConfigListUpRes{
        println("[[[[[[[[[[[[[[[[[[--------- 부가기능 리스트업 컨트롤러---------]]]]]]]]]]]]]]]]]")
        return serviceProvider.campingcarService.configListUp()
    }
}