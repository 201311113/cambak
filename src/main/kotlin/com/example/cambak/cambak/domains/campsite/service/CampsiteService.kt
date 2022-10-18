package com.example.cambak.cambak.domains.campsite.service

import com.example.cambak.cambak.domains.campsite.model.CampsiteDto

interface CampsiteService {
    fun create(
        req: CampsiteDto.CreateCampsiteReq
    ):CampsiteDto.CreateCampsiteRes

    fun update(
        req: CampsiteDto.UpdateCampsiteReq
    ): CampsiteDto.UpdateCampsiteRes

    fun getList(

    ): CampsiteDto.GetCampsiteListRes

    fun getDetail(
        campsiteId:String
    ): CampsiteDto.GetCampsiteDetailRes

    fun delete(
        req: CampsiteDto.DeleteCampsiteReq
    ): CampsiteDto.DeleteCampsiteRes
}