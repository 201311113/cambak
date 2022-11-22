package com.example.cambak.cambak.domains.place.carpark.controller

import com.example.cambak.cambak.common.util.BadRequestException
import com.example.cambak.cambak.common.util.ServiceProvider
import com.example.cambak.cambak.domains.place.carpark.model.CarparkDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/carpark")
@Api(tags = ["[Carpark] 차박지 관련 API"])
class CarparkController {

    @Autowired
    lateinit var serviceProvider: ServiceProvider

    @ApiOperation(
        value = "[DEV] 차박지 등록",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PostMapping("/create")
    fun create(
        @RequestBody req: CarparkDto.CreateCarparkReq
    ):CarparkDto.CreateCarparkRes{
        return serviceProvider.carparkService.create(req)
    }

    @ApiOperation(
        value = "[DEV] 차박지 수정 ",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PutMapping("/update")
    fun update(
        @RequestBody req: CarparkDto.UpdateCarparkReq
    ):CarparkDto.UpdateCarparkRes{
        return serviceProvider.carparkService.update(req)
    }

    @ApiOperation(
        value = "[DEV] 차박지 이미지 등록 및 수정",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @RequestMapping(
        value = ["/image"],
        method = [RequestMethod.POST],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun uploadImages(
        @RequestPart carparkId: String,
        imageReq: List<MultipartFile>?,
        @RequestPart deleteImageIds: String?
    ):CarparkDto.UploadImageRes{
        return serviceProvider.carparkService.uploadImages(carparkId,imageReq,deleteImageIds)
    }

    @ApiOperation(
        value = "차박지 상세정보",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/{carparkId}")
    fun getDetail(
        @PathVariable carparkId: String
    ): CarparkDto.GetCarparkDetail{
        try {
            return serviceProvider.carparkService.getDetail(carparkId)
        }catch (e:BadRequestException){
            return CarparkDto.GetCarparkDetail(e.code)
        }
    }


}