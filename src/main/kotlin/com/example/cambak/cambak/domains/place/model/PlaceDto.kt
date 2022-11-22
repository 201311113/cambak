package com.example.cambak.cambak.domains.place.model

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.database.entity.place.Region
import io.swagger.annotations.ApiModelProperty

class PlaceDto {
    //create
    class CreatePlaceReq(
        //TODO: 요구사항 있을 시 개발
    )
    class CreatePlaceRes(
        code: Int,
        var placeId: String ?= null
    ):Response(code)

    //update
    class UpdatePlaceReq(
        //TODO: 요구사항 있을 시 개발
    )
    class UpdatePlaceRes(
        code: Int,
        var placeId: String ?= null
    ):Response(code)

    //get List
    class GetPlaceListReq(

    )
    class GetPlaceListRes(
        code: Int,
        var placeList: List<PlaceInfo> ?= null
    ):Response(code){
        class PlaceInfo(
            @ApiModelProperty(name = "장소 id")
            var id: String,
            @ApiModelProperty(name = "장소 이름")
            var name: String,
            @ApiModelProperty(name = "장소 타입", value = "[CAMPSITE,CARPARK]")
            var placeType: String,//캠핑장, 차박지
            @ApiModelProperty(name = "장소 주소")
            var address: String,
            @ApiModelProperty(name = "장소 위도")
            var lat: Double,
            @ApiModelProperty(name = "장소 경도")
            var lng: Double,
//            var totalScore: Double,//평점 추후 개발
            @ApiModelProperty(name = "장소 가능 차종류", value = "[BASIC(일반차량), TRAILER(트레일러), CARAVAN(카라반), MOTERHUM(모터훔)]")
            var possibleCarType: List<String>,
            @ApiModelProperty(name = "장소 대표로 display할 기본, 부대시설", value = """
                해시태그 = [추후 예시 추가],
                기본시설 = ELECTRIC(전기),CONVENIENCE_STORE(편의점/매점),GARBAGE_DUMP(쓰레기장),SHOWER_ROOM(샤워실),SINK(개수대), TOILET(화장실),WIFI(와이파이)
                부대시설 = AMUSEMENT("놀이시설"), FIREWOOD_SALE(장작판매),GYM(운동시설), SWIMMING_POOL(수영장), TRAIL(산책로)
                이용규칙 = SELF_CATERING("취사가능"), PET(반려동물)
            """)
            var configList: List<String>,//TODO: 대표 시설 뭐로 픽스되는지 물어보기
            @ApiModelProperty(name = "이미지 리스트")
            var imageUrlList: List<String>
        )
    }

    class GetFilterTypeListRes(
        code: Int,
        @ApiModelProperty(name = "지역 필터타입", value = "SEOULANDGYEONGGI(\"서울/경기\"),INCHEON(\"인천\"),GANGWON(\"강원\"),CHUNGBUK(\"충북\"),CHUNGNAM(\"충남\"),\n" +
                "    GYEONGNAM(\"경남\"),GYEONGBUK(\"경북\"),JEONBUK(\"전북\"),JEONNAM(\"전남\"),JEJU(\"제주\")")
        var regionTypeList: List<String>,
        @ApiModelProperty(name = "차량 종류 필터타입", value = "BASIC(일반차량), TRAILER(트레일러), CARAVAN(카라반), MOTERHUM(모터훔) // 중복불가")
        var carTypeList: List<String>,
        @ApiModelProperty(name = "캠핑 유형 타입", value = "CAMPSITE,CARPARK")
        var campTypeList: List<String>,
        @ApiModelProperty(name = "기본시설 필터타입", value = "부대시설 = AMUSEMENT(\"놀이시설\"), FIREWOOD_SALE(장작판매),GYM(운동시설), SWIMMING_POOL(수영장), TRAIL(산책로)")
        var facilityTypeList: List<String>,
        @ApiModelProperty(name = "부대시설 필터타입", value = "기본시설 = ELECTRIC(전기),CONVENIENCE_STORE(편의점/매점),GARBAGE_DUMP(쓰레기장),SHOWER_ROOM(샤워실),SINK(개수대), TOILET(화장실),WIFI(와이파이)\\n\"")
        var amenityTypeList: List<String>,
        @ApiModelProperty(name = "이용규칙 필터타입", value = "SELF_CATERING(\"취사가능\"), PET(반려동물)")
        var ruleTypeList: List<String>,
        @ApiModelProperty(name = "환경 필터타입", value = "SEA(\"바다\"),VALLEY(\"계곡(강/호수)\"), MT(\"산\")")
        var envTypeList: List<String>
    ):Response(code)

    //get detail
    class GetPlaceDetailReq(

    )
    class GetPlaceDetailRes(
        code: Int,
        var placeDetail: PlaceDetail ?= null
    ):Response(code){
        class PlaceDetail(
            var id: String,
            var name: String,
            var address: String,
            var oldAddress: String,
            var bio: String?,
            var isClosed: Boolean,
            var lat: Double?,
            var lng: Double?,
            var likes: Long,
            var price: Long?,
            var priceDescription: String?,
            var contact: String?,
            var websiteUrl: String?,
            var score: Long,
            var view: Long,
            var themeId: String?,
            var region: Region?,
            var mainImageId: String?,
            var search: String?,
            var filter: String,
            var isCampsite: Boolean,
        )
    }

    //delete
    class DeletePlaceReq(

    )
    class DeletePlaceRes(
        code: Int,
        var placeId: String ?= null
    ):Response(code)
}