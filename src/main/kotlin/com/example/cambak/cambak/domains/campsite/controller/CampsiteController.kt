package com.example.cambak.cambak.domains.campsite.controller

import com.example.cambak.cambak.common.util.BadRequestException
import com.example.cambak.cambak.common.util.OK
import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.common.util.ServiceProvider
import com.example.cambak.cambak.domains.campsite.model.CampsiteDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/campsite")
@Api(tags = ["[CampSite] 캠핑장 관련 API"])
class CampsiteController {

    @Autowired
    lateinit var serviceProvider: ServiceProvider

    @ApiOperation(
        value = "[DEV] 캠핑장 등록",
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
        value = "캠핑장 이미지 등록 및 수정",
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
        @RequestPart campsiteId: String,
        imageReq: List<MultipartFile>?,
        layoutReq: List<MultipartFile>?,
        @RequestPart deleteImageIds: String?
    ):CampsiteDto.UploadImageRes{
        try {
            return serviceProvider.campsiteService.uploadImages(campsiteId, imageReq, layoutReq,deleteImageIds)
        }catch (e: BadRequestException){
            return CampsiteDto.UploadImageRes(e.code)
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
        value = "캠핑장 상세정보",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/{campsiteId}")
    fun getDetail(
        @PathVariable campsiteId: String
    ):CampsiteDto.GetCampsiteDetailRes{
        try {
            return serviceProvider.campsiteService.getDetail(campsiteId)
        }catch (e: BadRequestException){
            return CampsiteDto.GetCampsiteDetailRes(e.code)
        }
    }



//    @ApiOperation(
//        value = "캠핑장 이미지 리스트업",
//        notes = """
//            code 0 : 성공
//            code -1: 알 수 없는 오류
//        """
//    )
//    @GetMapping("/images/{campsiteId}")
//    fun getCampsiteImages(
//        @PathVariable campsiteId: String,
//    ): CampsiteDto.GetCampsiteImageListRes{
//        try {
//            return CampsiteDto.GetCampsiteImageListRes(OK)  //TODO: 개발해
//        }catch (e: BadRequestException){
//            return CampsiteDto.GetCampsiteImageListRes(e.code)
//        }
//    }




//    @ApiOperation(
//        value = "캠핑장 삭제",
//        notes = """
//            code 0 : 성공
//            code -1: 알 수 없는 오류
//        """
//    )
//    @DeleteMapping("/delete")
//    fun getList(
//        @RequestBody req: CampsiteDto.DeleteCampsiteReq
//    ): CampsiteDto.DeleteCampsiteRes{
//        try {
//            return serviceProvider.campsiteService.delete(req)
//        }catch (e: BadRequestException){
//            return CampsiteDto.DeleteCampsiteRes(e.code)
//        }
//    }
}