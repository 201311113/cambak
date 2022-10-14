package com.example.cambak.cambak.domains.campingcar.service

import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto
import com.example.cambak.cambak.domains.campingcar.model.CampingCarFilterType
import org.springframework.web.multipart.MultipartFile

interface CampingCarService {
    fun enroll(
        req: CampingCarDto.EnrollCampingCarReq
    ):CampingCarDto.EnrollCampingCarRes

    fun update(
        req: CampingCarDto.UpdateCampingCarReq
    ):CampingCarDto.UpdateCampingCarRes

    fun uploadImages(
        campingCarId: String,
        images: List<MultipartFile>
    ):CampingCarDto.CampingCarImageRes

    fun delete(
        campingCarId: String
    ):CampingCarDto.DeleteCampingCarRes

    fun listUp(
        filterTypeList: List<CampingCarFilterType>?
    ):CampingCarDto.GetCampingCarListRes

    fun getDetail(
        campingCarId: String
    ):CampingCarDto.GetCampingCarDetailRes

    fun configListUp(): CampingCarDto.ConfigListUpRes
}