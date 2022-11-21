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
            @ApiModelProperty(name = "장소 대표로 display할 기본, 부대시설")
            var configList: List<String>//TODO: 대표 시설 뭐로 픽스되는지 물어보기
        )
    }

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