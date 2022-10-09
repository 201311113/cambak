package com.example.cambak.cambak.domains.campingcar.service

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.domains.campingcar.model.CampingCarConfigDto

interface CampingCarConfigService {
    fun addConfig(
        req: CampingCarConfigDto.AddConfigReq
    ): Response
    fun addConfigType(
        req: CampingCarConfigDto.AddConfigTypeReq
    ): Response
}