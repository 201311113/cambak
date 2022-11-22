package com.example.cambak.cambak.domains.place.carpark.service

import com.example.cambak.cambak.domains.place.carpark.model.CarparkDto
import org.springframework.web.multipart.MultipartFile

interface CarparkService {
    fun create(
        req:CarparkDto.CreateCarparkReq
    ):CarparkDto.CreateCarparkRes

    fun update(
        req: CarparkDto.UpdateCarparkReq
    ):CarparkDto.UpdateCarparkRes

    fun uploadImages(
        carparkId: String,
        imageReq: List<MultipartFile>?,
        deleteImageIds: String?
    ):CarparkDto.UploadImageRes

    fun getDetail(
        carparkId: String
    ):CarparkDto.GetCarparkDetail
}