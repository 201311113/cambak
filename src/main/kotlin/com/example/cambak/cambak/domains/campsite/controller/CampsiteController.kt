package com.example.cambak.cambak.domains.campsite.controller

import com.example.cambak.cambak.common.util.BadRequestException
import com.example.cambak.cambak.common.util.OK
import com.example.cambak.cambak.common.util.ServiceProvider
import com.example.cambak.cambak.domains.campsite.model.CampsiteDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/campsite")
@Api(tags = ["[CampSite] 캠핑장 관련 API"])
class CampsiteController {

    @Autowired
    lateinit var serviceProvider: ServiceProvider

    @ApiOperation(
        value = "캠핑장 등록",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PostMapping("/create")
    fun create(
        @RequestBody req: CampsiteDto.CreateCampsiteReq
    ):CampsiteDto.CreateCampsiteRes{
        try {
            return serviceProvider.campsiteService.create(req)
        }catch (e: BadRequestException){
            return CampsiteDto.CreateCampsiteRes(e.code)
        }
    }

    @ApiOperation(
        value = "캠핑장 수정",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PutMapping("/update")
    fun update(
        @RequestBody req: CampsiteDto.UpdateCampsiteReq
    ): CampsiteDto.UpdateCampsiteRes {
        try {
            return serviceProvider.campsiteService.update(req)
        }catch (e: BadRequestException){
            return CampsiteDto.UpdateCampsiteRes(e.code)
        }
    }

    @ApiOperation(
        value = "캠핑장 리스트 조회",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    fun getList(

    ): CampsiteDto.GetCampsiteListRes{
        try {
            return serviceProvider.campsiteService.getList()
        }catch (e: BadRequestException){
            return CampsiteDto.GetCampsiteListRes(e.code)
        }
    }
    @ApiOperation(
        value = "캠핑장 상세 조회",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/{campsiteId}")
    fun getList(
        @PathVariable campsiteId: String
    ): CampsiteDto.GetCampsiteDetailRes{
        try {
            return serviceProvider.campsiteService.getDetail(campsiteId)
        }catch (e: BadRequestException){
            return CampsiteDto.GetCampsiteDetailRes(e.code)
        }
    }

    @ApiOperation(
        value = "캠핑장 삭제",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @DeleteMapping("/delete")
    fun getList(
        @RequestBody req: CampsiteDto.DeleteCampsiteReq
    ): CampsiteDto.DeleteCampsiteRes{
        try {
            return serviceProvider.campsiteService.delete(req)
        }catch (e: BadRequestException){
            return CampsiteDto.DeleteCampsiteRes(e.code)
        }
    }
}