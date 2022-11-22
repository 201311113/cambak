package com.example.cambak.cambak.domains.place.controller

import com.example.cambak.cambak.common.util.BadRequestException
import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.common.util.ServiceProvider
import com.example.cambak.cambak.domains.place.model.PlaceDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


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
        value = "장소 필터링 조회 리스트업",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @ApiImplicitParams(
        ApiImplicitParam(
            name = "filterTypeList", value = """
            figma 3-1-2, 3-1-3의 필터타입 리스트
            캠핑유형 = CAMPSITE(캠핑장),CARPARK(차박지)
            해시태그 = [추후 예시 추가],
            기본시설 = ELECTRIC(전기),CONVENIENCE_STORE(편의점/매점),GARBAGE_DUMP(쓰레기장),SHOWER_ROOM(샤워실),SINK(개수대), TOILET(화장실),WIFI(와이파이)
            부대시설 = AMUSEMENT("놀이시설"), FIREWOOD_SALE(장작판매),GYM(운동시설), SWIMMING_POOL(수영장), TRAIL(산책로)
            이용규칙 = SELF_CATERING("취사가능"), PET(반려동물)
            지역 = SEOULANDGYEONGGI("서울/경기"),INCHEON("인천"),GANGWON("강원"),CHUNGBUK("충북"),CHUNGNAM("충남"),
    GYEONGNAM("경남"),GYEONGBUK("경북"),JEONBUK("전북"),JEONNAM("전남"),JEJU("제주")
            
            차종류 = BASIC(일반차량), TRAILER(트레일러), CARAVAN(카라반), MOTERHUM(모터훔)
            자연환경 = SEA("바다"),VALLEY("계곡(강/호수)"), MT("산")
            
            
        """, required = false
        )
    )
    @GetMapping("")
    fun getList(
        @RequestParam filterTypeList: List<String>
    ):PlaceDto.GetPlaceListRes{
        try {
            return serviceProvider.placeService.getList(filterTypeList)
        }catch (e: BadRequestException){
            return PlaceDto.GetPlaceListRes(e.code)
        }
    }

    @ApiOperation(
        value = "장소 필터링 시 필요한 필터타입들 리스트업",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/filter-type")
    fun getFilterTypeList(

    ):PlaceDto.GetFilterTypeListRes{
        return serviceProvider.placeService.getFilterTypeList()
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