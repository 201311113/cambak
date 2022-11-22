package com.example.cambak.cambak.domains.place.place.service

import com.example.cambak.cambak.domains.place.place.model.PlaceDto

interface PlaceService {
    fun create(
        req: PlaceDto.CreatePlaceReq
    ): PlaceDto.CreatePlaceRes

    fun update(
        req: PlaceDto.UpdatePlaceReq
    ): PlaceDto.UpdatePlaceRes

    fun getList(
        filterTypeList: List<String>
    ): PlaceDto.GetPlaceListRes

    fun getFilterTypeList(

    ): PlaceDto.GetFilterTypeListRes

    fun getDetail(
        placeId: String
    ): PlaceDto.GetPlaceDetailRes

    fun delete(
        req: PlaceDto.DeletePlaceReq
    ): PlaceDto.DeletePlaceRes
}