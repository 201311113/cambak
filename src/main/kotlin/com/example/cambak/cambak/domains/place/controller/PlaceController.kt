package com.example.cambak.cambak.domains.place.controller

import com.example.cambak.cambak.common.util.BadRequestException
import com.example.cambak.cambak.common.util.ServiceProvider
import com.example.cambak.cambak.domains.place.model.PlaceDto
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
@RequestMapping("/place")
@Api(tags = ["[Place] 장소 관련 API"])
class PlaceController {

    @Autowired
    lateinit var serviceProvider: ServiceProvider

//    @ApiOperation(
//        value = "장소 등록",
//        notes = """
//            code 0 : 성공
//            code -1: 알 수 없는 오류
//        """
//    )
//    @PostMapping("/create")
//    fun create(
//        @RequestBody req: PlaceDto.CreatePlaceReq
//    ): PlaceDto.CreatePlaceRes{
//        try {
//            return serviceProvider.placeService.create(req)
//        }catch (e: BadRequestException){
//            return PlaceDto.CreatePlaceRes(e.code)
//        }
//    }
//
//    @ApiOperation(
//        value = "장소 수정",
//        notes = """
//            code 0 : 성공
//            code -1: 알 수 없는 오류
//        """
//    )
//    @PutMapping("/update")
//    fun update(
//        @RequestBody req: PlaceDto.UpdatePlaceReq
//    ): PlaceDto.UpdatePlaceRes{
//        try{
//            return serviceProvider.placeService.update(req)
//        }catch (e: BadRequestException){
//            return PlaceDto.UpdatePlaceRes(e.code)
//        }
//    }


    @ApiOperation(
        value = "장소 리스트 업 ",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/list")
    fun getList(

    ): PlaceDto.GetPlaceListRes{
        try {
            return serviceProvider.placeService.getList()
        }catch (e: BadRequestException){
            return PlaceDto.GetPlaceListRes(e.code)
        }
    }


    @ApiOperation(
        value = "장소 상세정보 조회",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/{placeId}")
    fun getDetail(
        @PathVariable placeId: String
    ): PlaceDto.GetPlaceDetailRes{
        try {
            return serviceProvider.placeService.getDetail(placeId)
        }catch (e: BadRequestException){
            return PlaceDto.GetPlaceDetailRes(e.code)
        }
    }

//    @ApiOperation(
//        value = "장소 삭제",
//        notes = """
//            code 0 : 성공
//            code -1: 알 수 없는 오류
//        """
//    )
//    @DeleteMapping("")
//    fun delete(
//        req: PlaceDto.DeletePlaceReq
//    ): PlaceDto.DeletePlaceRes{
//        try {
//            return serviceProvider.placeService.delete(req)
//        }catch (e: BadRequestException){
//            return PlaceDto.DeletePlaceRes(e.code)
//        }
//    }




}