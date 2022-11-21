package com.example.cambak.cambak.domains.campsite.service

import com.example.cambak.cambak.domains.campsite.model.CampsiteDto
import org.springframework.web.multipart.MultipartFile

interface CampsiteService {


    fun getDetail(
        campsiteId:String
    ): CampsiteDto.GetCampsiteDetailRes

    fun create(
        req: CampsiteDto.CreateCampsiteReq
    ):CampsiteDto.CreateCampsiteRes
    fun uploadImages(
        campsiteId: String,
        imageReq: List<MultipartFile>?,
        layoutReq: List<MultipartFile>?,
        deleteImageIds: String?
    ): CampsiteDto.UploadImageRes

    fun update(
        req: CampsiteDto.UpdateCampsiteReq
    ): CampsiteDto.UpdateCampsiteRes


//    fun delete(
//        req: CampsiteDto.DeleteCampsiteReq
//    ): CampsiteDto.DeleteCampsiteRes
}