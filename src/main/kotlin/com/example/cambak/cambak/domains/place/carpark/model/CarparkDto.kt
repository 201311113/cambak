package com.example.cambak.cambak.domains.place.carpark.model

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.database.entity.place.CampType
import com.example.cambak.database.entity.place.Environment
import com.example.cambak.database.entity.place.FloorGround
import com.example.cambak.database.entity.place.Region
import io.swagger.annotations.ApiModelProperty

class CarparkDto {
    class CreateCarparkReq(
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
    class CreateCarparkRes(
        code:Int,
        var carparkId: String ?= null
    ):Response(code)

    class UpdateCarparkReq(
        @ApiModelProperty(name = "장소 id")
        var id: String,
        @ApiModelProperty(name = "장소 이름")
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

    class UpdateCarparkRes(
        code: Int,
        var carparkId: String ?= null
    ):Response(code)

    class UploadImageRes(
        code: Int,
    ):Response(code)

    class GetCarparkDetail(
        code: Int,
        var carparkDetail: CarparkDetail ?= null
    ): Response(code){
        class CarparkDetail(
            @ApiModelProperty(name = "장소 id")
            var id: String,
            @ApiModelProperty(name = "장소 이름")
            var name: String,
            @ApiModelProperty(name = "장소 타입", value = "CAMPSITE, CARPARK")
            var placeType: String,
            @ApiModelProperty(name = "환경", value = "MT(산), VALLEY(계곡), SEA(바다)")
            var environment: Environment,
            @ApiModelProperty(name = "장소 주소")
            var address: String,
            @ApiModelProperty(name = "지역", value = """
                "SEOULANDGYEONGGI", "INCHEON", "GANGWON", "CHUNGBUK", "CHUNGNAM",
            "GYEONGNAM", "GYEONGBUK", "JEONBUK", "JEONNAM", "JEJU"
            """)
            var region: Region,
            @ApiModelProperty(name = "이미지 url")
            var imageUrlList: List<String> ?= null,
            @ApiModelProperty(name = "한줄 소개")
            var oneLineDescription: String,
            @ApiModelProperty(name = "해시태그" ,value = """
                해시태그 = [추후 예시 추가],
            이용규칙 = SELF_CATERING("취사가능"), PET(반려동물)
            """)
            var hashTags: List<String>?,
            @ApiModelProperty(name = "상세소개")
            var description: String,
            @ApiModelProperty(name = "시설 안내 (기본시설)", value = """        
                기본시설 = ELECTRIC(전기),CONVENIENCE_STORE(편의점/매점),GARBAGE_DUMP(쓰레기장),SHOWER_ROOM(샤워실),SINK(개수대), TOILET(화장실),WIFI(와이파이)
                """)
            var basicConfigList: List<String>?,
            @ApiModelProperty(name = "시설 안내 (부대시설)", value = """
                부대시설 = AMUSEMENT("놀이시설"), FIREWOOD_SALE(장작판매),GYM(운동시설), SWIMMING_POOL(수영장), TRAIL(산책로)
            """)
            var amenityConfigList: List<String>?,
            @ApiModelProperty(name = "캠핑존 정보의 진입 가능 차량")
            var possibleCarType: List<String>,
            @ApiModelProperty(name = "위도")
            var lat: Double,
            @ApiModelProperty(name = "경도")
            var lng: Double,
            @ApiModelProperty(name = "전화번호")
            var phoneNo: String,
            @ApiModelProperty(name = "평점")
            var score: Double,
            @ApiModelProperty(name = "찜 여부(추후개발 Default = false)")
            var isLiked: Boolean,
            @ApiModelProperty(name = "블로그 url 리스트")
            var blogUrlList: List<String>?,
            @ApiModelProperty(name = "유튜브 url 리스트")
            var youtubeUrls: List<String>?,
        )
    }
}