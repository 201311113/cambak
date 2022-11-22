package com.example.cambak.cambak.domains.place.place.service.impl

import com.example.cambak.cambak.common.util.OK
import com.example.cambak.cambak.common.util.RepositoryProvider
import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.common.util.UNKNOWN_ERROR
import com.example.cambak.cambak.domains.place.place.controller.CreateConfigReq
import com.example.cambak.cambak.domains.place.place.service.PlaceDevService
import com.example.cambak.database.entity.place.Place
import com.example.cambak.database.entity.place.PlaceConfig
import com.example.cambak.database.entity.place.PlaceTemp
import com.example.cambak.database.entity.place.Region
import org.springframework.stereotype.Service

@Service
class PlaceDevServiceImpl(
    var repo: RepositoryProvider
): PlaceDevService {

    override fun migration(): List<Place> {

        return repo.placeRepository.findAll()
    }

    override fun createConfig(req: CreateConfigReq): Response {
        if(req.placeConfigKey == "" || req.placeConfigName == "") return Response(UNKNOWN_ERROR)

        repo.placeConfigRepository.save(
            PlaceConfig(
                placeConfigKey = req.placeConfigKey,
                placeConfigName = req.placeConfigName,
                placeConfigType = req.placeConfigType,
            )
        )
        return Response(OK)
    }

}