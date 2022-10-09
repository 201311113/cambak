package com.example.cambak.cambak.domains.campingcar.service

import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto

interface CampingCarService {
    fun enroll(
        req: CampingCarDto.EnrollCampingCarReq
    ):CampingCarDto.EnrollCampingCarRes

    fun configListUp(): CampingCarDto.ConfigListUpRes
}