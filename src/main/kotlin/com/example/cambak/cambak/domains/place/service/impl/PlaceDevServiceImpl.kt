package com.example.cambak.cambak.domains.place.service.impl

import com.example.cambak.cambak.common.util.RepositoryProvider
import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.domains.place.service.PlaceDevService
import com.example.cambak.database.entity.place.PlaceTemp
import org.springframework.stereotype.Service

@Service
class PlaceDevServiceImpl(
    var repo: RepositoryProvider
): PlaceDevService {

    override fun migration(): List<PlaceTemp> {
        val list = repo.placeRepository.findAll()



        return repo.placeRepository.findAll()
    }

}