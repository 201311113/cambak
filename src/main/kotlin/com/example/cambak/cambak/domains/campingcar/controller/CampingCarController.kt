package com.example.cambak.cambak.domains.campingcar.controller

import com.example.cambak.cambak.common.util.BadRequestException
import com.example.cambak.cambak.common.util.OK
import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.common.util.ServiceProvider
import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto
import com.example.cambak.cambak.domains.campingcar.model.CampingCarFilterType
import com.mysql.cj.x.protobuf.Mysqlx.Ok
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

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
        try{
            return serviceProvider.campingcarService.enroll(req)
        }catch (e: BadRequestException){
            return CampingCarDto.EnrollCampingCarRes(e.code)
        }
    }
    @ApiOperation(
        value = "캠핑카 정보 수정",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PutMapping("/update")
    fun update(
        @RequestBody req: CampingCarDto.UpdateCampingCarReq
    ): CampingCarDto.UpdateCampingCarRes{
        try {
            return serviceProvider.campingcarService.update(req)
        }catch (e: BadRequestException){
            return CampingCarDto.UpdateCampingCarRes(e.code)
        }
    }

    @ApiOperation(
        value = "캠핑카 이미지 등록 및 수정",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @RequestMapping(
        value = ["/report/picture"],
        method = [RequestMethod.POST],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun enrollImages(
        @RequestPart campingCarId: String,
        imageReq: List<MultipartFile>
    ):CampingCarDto.CampingCarImageRes{
        return serviceProvider.campingcarService.uploadImages(campingCarId,imageReq)
    }

    @ApiOperation(
        value = "캠핑카 리스트업",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/list")
    fun getCampingCarList(
        @RequestParam filterTypeList: List<CampingCarFilterType>?,
    ):CampingCarDto.GetCampingCarListRes{
        try{
            return serviceProvider.campingcarService.listUp(emptyList())
        }catch (e: BadRequestException){
            return CampingCarDto.GetCampingCarListRes(e.code)
        }

    }

    @ApiOperation(
        value = "캠핑카 상세정보",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/{campingCarId}")
    fun getCampingCarDetail(
        @PathVariable campingCarId: String
    ): CampingCarDto.GetCampingCarDetailRes{
        try {
            return serviceProvider.campingcarService.getDetail(campingCarId)
        }catch (e: BadRequestException){
            return CampingCarDto.GetCampingCarDetailRes(e.code)
        }
    }

    @ApiOperation(
        value = "캠핑카 삭제",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @DeleteMapping("/{campingCarId}")
    fun delete(
        @PathVariable campingCarId: String
    ): CampingCarDto.DeleteCampingCarRes{
        return serviceProvider.campingcarService.delete(campingCarId)
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
        return serviceProvider.campingcarService.configListUp()
    }
}