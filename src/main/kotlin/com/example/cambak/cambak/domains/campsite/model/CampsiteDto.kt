package com.example.cambak.cambak.domains.campsite.model

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.database.entity.place.CampType
import com.example.cambak.database.entity.place.Environment
import com.example.cambak.database.entity.place.FloorGround
import com.example.cambak.database.entity.place.Region
import io.swagger.annotations.ApiModelProperty

class CampsiteDto {
    //create
    class CreateCampsiteReq(
        @ApiModelProperty(name = "장소 이름")
        var name: String,
        @ApiModelProperty(name = "주소")
        var address: String,
        @ApiModelProperty(name = "한줄 소개")
        var oneLineDescription: String,
        @ApiModelProperty(name = "상세 소개")
        var description: String,
        @ApiModelProperty(name = "위도")
        var lat: Double,
        @ApiModelProperty(name = "경도")
        var lng: Double,
        @ApiModelProperty(name = "전화번호")
        var phoneNo: String,
        @ApiModelProperty(name = "지역", value = """
    SEOULANDGYEONGGI("서울/경기"),INCHEON("인천"),GANGWON("강원"),CHUNGBUK("충북"),CHUNGNAM("충남"),
    GYEONGNAM("경남"),GYEONGBUK("경북"),JEONBUK("전북"),JEONNAM("전남"),JEJU("제주")
        """)
        var region: Region,
        @ApiModelProperty(name = "환경" ,value = """
    SEA("바다"),VALLEY("계곡(강/호수)"), MT("산")
        """)
        var environment: Environment,
        @ApiModelProperty(value = "일반차량, 트레일러, 카라반, 모터훔 순서대로 binary string  ex) 1011 == [일반차량, 카라반, 모터훔] / 0101 == [트레일러, 모터훔]")
        var possibleCarType: String,
        @ApiModelProperty(value = "웹사이트 주소 [nullable]",)
        var websiteUrl: String?,
        @ApiModelProperty(value = "가격 설명 [nullable]")
        var priceDescription: String?,
        @ApiModelProperty(value = "매너타임 설명 [nullable] ")
        var mannerTimeDescription: String?,
        @ApiModelProperty(name = "운영형태", value = "AUTO_CAMPING, GLAMPING, CARAVAN")
        var campType: CampType, //[운영 형태 : 오토캠핑, 글램핑, 카라반]
        @ApiModelProperty(name = "캠핑장 지면 = [마사토], [데크], [야자매트], [콘크리트], [파쇄석], [자갈], [잔디], [기타]", value = "ex) [MASATO(\"마사토\"), DECK(\"데크\"), PALM_MAT(\"야자매트\"), CONCRETE(\"콘크리트\"),\n" +
                "    CRUSHED_STONE(\"파쇄석\"),PEBBLE(\"자갈\"), GRASS(\"잔디\"), OTHERS(\"기타\")]")
        var floorGround: FloorGround,   //    항목: [마사토], [데크], [야자매트], [콘크리트], [파쇄석], [자갈], [잔디], [기타]    표시(타입) : 아이콘(img) + 지면 종류(txt)
        @ApiModelProperty(value = "캠핑장 크기")
        var campsiteSize: String,   // “[nn] X [nn]m”(txt)
        @ApiModelProperty(value = "캠핑장 간격")
        var campsiteSpace: String,   // “[NN]m” (txt)
        @ApiModelProperty(value = "해시태그")
        var hashTags: List<String>?,   // placeconfigkey list / type = ConfigType.HASHTAG
        @ApiModelProperty(name = "기본 시설",value = "[SINK(개수대), WIFI(와이파이), TOILET(화장실), ELECTRIC(전기), SHOWER_ROOM(샤워실), GARBAGE_DUMP(쓰레기장), CONVENIENCE_STORE(편의점/매점)]")
        var facilities: List<String>?,    //type = ConfigType.FACILITIES
        @ApiModelProperty(name = "부대시설", value = "[GYM(운동시설), TRAIL(산책로), FIREWOOD_SALE(장작판매), AMUSEMENT(놀이시설), SWIMMING_POOL(수영장)]")
        var amenities: List<String>?,    //type = ConfigType.AMENITIES
        @ApiModelProperty(name = "이용 규칙", value = "[SELF_CATERING(취사가능), PET(반려동물)]")
        var rules: List<String>?,         //type = ConfigType.RULES
        //++ 배치도는 id로 매핑된 image.class에 type = CAMPSITE_LAYOUT
        @ApiModelProperty(name = "블로그 url리스트")
        var blogUrls: List<String>?,
        @ApiModelProperty(name = "유튜브 url리스트")
        var youtubeUrls: List<String>?,

    )
    class CreateCampsiteRes(
        code: Int,
        var placeId: String ?= null
    ): Response(code)

    //update
    class UpdateCampsiteReq(

        @ApiModelProperty(name = "캠핑장 id")
        var id: String,
        @ApiModelProperty(name = "캠핑장 이름")
        var name: String?,
        @ApiModelProperty(name = "주소")
        var address: String?,
        @ApiModelProperty(name = "한줄 소개")
        var oneLineDescription: String?,
        @ApiModelProperty(name = "상세 소개")
        var description: String?,
        @ApiModelProperty(name = "위도")
        var lat: Double?,
        @ApiModelProperty(name = "경도")
        var lng: Double?,
        @ApiModelProperty(name = "전화번호")
        var phoneNo: String?,
        @ApiModelProperty(name = "지역", value = """
    SEOULANDGYEONGGI("서울/경기"),INCHEON("인천"),GANGWON("강원"),CHUNGBUK("충북"),CHUNGNAM("충남"),
    GYEONGNAM("경남"),GYEONGBUK("경북"),JEONBUK("전북"),JEONNAM("전남"),JEJU("제주")
        """)
        var region: Region?,
        @ApiModelProperty(name = "환경" ,value = """
    SEA("바다"),VALLEY("계곡(강/호수)"), MT("산")
        """)
        var environment: Environment?,
        @ApiModelProperty(value = "일반차량, 트레일러, 카라반, 모터훔 순서대로 binary string  ex) 1011 == [일반차량, 카라반, 모터훔] / 0101 == [트레일러, 모터훔]")
        var possibleCarType: String?,
        @ApiModelProperty(value = "웹사이트 주소 [nullable]",)
        var websiteUrl: String?,
        @ApiModelProperty(value = "가격 설명 [nullable]")
        var priceDescription: String?,
        @ApiModelProperty(value = "매너타임 설명 [nullable] ")
        var mannerTimeDescription: String?,
        @ApiModelProperty(name = "운영형태", value = "AUTO_CAMPING, GLAMPING, CARAVAN")
        var campType: CampType?, //[운영 형태 : 오토캠핑, 글램핑, 카라반]
        @ApiModelProperty(name = "캠핑장 지면 = [마사토], [데크], [야자매트], [콘크리트], [파쇄석], [자갈], [잔디], [기타]", value = "ex) [MASATO(\"마사토\"), DECK(\"데크\"), PALM_MAT(\"야자매트\"), CONCRETE(\"콘크리트\"),\n" +
                "    CRUSHED_STONE(\"파쇄석\"),PEBBLE(\"자갈\"), GRASS(\"잔디\"), OTHERS(\"기타\")]")
        var floorGround: FloorGround?,   //    항목: [마사토], [데크], [야자매트], [콘크리트], [파쇄석], [자갈], [잔디], [기타]    표시(타입) : 아이콘(img) + 지면 종류(txt)
        @ApiModelProperty(value = "캠핑장 크기")
        var campsiteSize: String?,   // “[nn] X [nn]m”(txt)
        @ApiModelProperty(value = "캠핑장 간격")
        var campsiteSpace: String?,   // “[NN]m” (txt)
        @ApiModelProperty(value = "해시태그")
        var hashTags: List<String>?,   // placeconfigkey list / type = ConfigType.HASHTAG
        @ApiModelProperty(name = "기본 시설",value = "[SINK(개수대), WIFI(와이파이), TOILET(화장실), ELECTRIC(전기), SHOWER_ROOM(샤워실), GARBAGE_DUMP(쓰레기장), CONVENIENCE_STORE(편의점/매점)]")
        var facilities: List<String>?,    //type = ConfigType.FACILITIES
        @ApiModelProperty(name = "부대시설", value = "[GYM(운동시설), TRAIL(산책로), FIREWOOD_SALE(장작판매), AMUSEMENT(놀이시설), SWIMMING_POOL(수영장)]")
        var amenities: List<String>?,    //type = ConfigType.AMENITIES
        @ApiModelProperty(name = "이용 규칙", value = "[SELF_CATERING(취사가능), PET(반려동물)]")
        var rules: List<String>?,         //type = ConfigType.RULES
        //++ 배치도는 id로 매핑된 image.class에 type = CAMPSITE_LAYOUT
        @ApiModelProperty(name = "블로그 url리스트")
        var blogUrls: List<String>?,
        @ApiModelProperty(name = "유튜브 url리스트")
        var youtubeUrls: List<String>?,
    )
    class UpdateCampsiteRes(
        code: Int,
        var placeId: String ?= null
    ): Response(code)

    //get list
    class GetCampsiteListRes(
        code: Int,
        var campsite: List<CampsiteInfo> ?= null
    ): Response(code){
        class CampsiteInfo(
            var id: String,
            var name: String?,
            var address: String?,
            var placeType: String,
            var imageUrlList: List<String>,
            var hashTags: List<String>,
            @ApiModelProperty(name = "평점")
            var score: Double,
            var lat: Double?,
            var lng: Double?,
        )
    }

    //get detail

    class GetCampsiteDetailRes(
        code: Int,

        ): Response(code)

    //delete

    class DeleteCampsiteReq(

    )
    class DeleteCampsiteRes(
        code: Int,

        ): Response(code)
    class UploadImageReq(

    )
    class UploadImageRes(
        code: Int
    ):Response(code)

    class GetCampsiteImageListRes(
        code: Int,
        basicImageUrlList: List<String> ?= null,

    ):Response(code)
}