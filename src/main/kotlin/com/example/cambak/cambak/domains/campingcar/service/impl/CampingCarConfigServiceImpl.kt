package com.example.cambak.cambak.domains.campingcar.service.impl

import com.example.cambak.cambak.common.util.*
import com.example.cambak.cambak.domains.campingcar.model.CampingCarConfigDto
import com.example.cambak.cambak.domains.campingcar.service.CampingCarConfigService
import com.example.cambak.database.entity.campingcar.CampingCarConfig
import org.springframework.stereotype.Service

@Service
class CampingCarConfigServiceImpl(

    var repo: RepositoryProvider,
): CampingCarConfigService {
    override fun addConfig(req: CampingCarConfigDto.AddConfigReq): Response {
        println("[[[[[[[[[[[[[[[[[[--------- 부가기능 추가 서비스---------]]]]]]]]]]]]]]]]]")
        if(!repo.campingCarConfigRepository.existsByConfigType(req.configType)) return Response(CONFIG_TYPE_NOT_FOUND)
        if(repo.campingCarConfigRepository.existsByCampingCarConfigKey(req.configKey)) return Response(ALREADY_CONFIG_KEY_EXIST)
        if(repo.campingCarConfigRepository.existsByCampingCarConfigName(req.configName)) return Response(ALREADY_CONFIG_KEY_EXIST)
        val campingCarConfig = CampingCarConfig(
            req.configKey,
            req.configName,
            req.configType
        )
        repo.campingCarConfigRepository.save(campingCarConfig)
        return Response(OK)
    }

    override fun addConfigType(req: CampingCarConfigDto.AddConfigTypeReq): Response {
        println("[[[[[[[[[[[[[[[[[[--------- 부가기능 타입 추가 서비스 ---------]]]]]]]]]]]]]]]]]")
        val campingCarConfig = CampingCarConfig(
            "admin",
            "admin",
            req.type
        )
        repo.campingCarConfigRepository.save(campingCarConfig)
        return Response(OK)
    }
}