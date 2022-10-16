package com.example.cambak.cambak.domains.place.model

import com.example.cambak.cambak.common.util.Response

class PlaceDto {
    //create
    class CreatePlaceReq(
        var name: String,
        var address: String,
        var addressDetail: String,
        var lat: Double,
        var lng: Double,
    )
    class CreatePlaceRes(
        code: Int,
        var placeId: String ?= null
    ):Response(code)

    //update
    class UpdatePlaceReq(
        var placeId: String,
        var name: String?,
        var address: String?,
        var addressDetail: String?,
        var lat: Double?,
        var lng: Double?,
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
            var placeId: String,
            var name: String,
            var address: String,
            var addressDetail: String,
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
            var placeId: String,
            var name: String,
            var address: String,
            var addressDetail: String,
            var lat: Double,
            var lng: Double,
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