package com.example.cambak.cambak.domains.place.model

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.database.entity.place.Region

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
            //TODO: FIX된 요구사항아님
            var id: String,
            var name: String,
            var address: String,
            var lat: Double,
            var lng: Double,
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