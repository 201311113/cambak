package com.example.cambak.cambak.domains.campsite.model

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.database.entity.place.Campsite

class CampsiteDto {
    //create
    class CreateCampsiteReq(
        var name: String,
        var address: String,
        var addressDetail: String,
        var lat: Double,
        var lng: Double,
    )
    class CreateCampsiteRes(
        code: Int,
        var placeId: String ?= null
    ): Response(code)

    //update
    class UpdateCampsiteReq(
        var id: String,
        var name: String?,
        var address: String?,
        var addressDetail: String?,
        var lat: Double?,
        var lng: Double?,
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
            var addressDetail: String?,
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
}