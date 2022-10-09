package com.example.cambak.cambak.domains.campingcar.service.impl

import com.example.cambak.cambak.common.util.OK
import com.example.cambak.cambak.common.util.RepositoryProvider
import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto
import com.example.cambak.cambak.domains.campingcar.service.CampingCarService
import org.springframework.stereotype.Service

@Service
class CampingCarServiceImpl(
    var repo: RepositoryProvider,
): CampingCarService {
    override fun enroll(
        req: CampingCarDto.EnrollCampingCarReq
    ): CampingCarDto.EnrollCampingCarRes {
        return CampingCarDto.EnrollCampingCarRes(OK)

    }

    override fun configListUp(): CampingCarDto.ConfigListUpRes {
        val configList = repo.campingCarConfigRepository.findAllByConfigType("basic")!!
            .stream()
            .map {
                CampingCarDto.ConfigListUpRes.Config(
                    it.campingCarConfigKey,
                    it.campingCarConfigName,
                )
            }.toList()
        return CampingCarDto.ConfigListUpRes(
            OK,
            configList
        )

    }
}