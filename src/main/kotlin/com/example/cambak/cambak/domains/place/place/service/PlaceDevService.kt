package com.example.cambak.cambak.domains.place.place.service

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.domains.place.place.controller.CreateConfigReq
import com.example.cambak.database.entity.place.Place
import com.example.cambak.database.entity.place.PlaceTemp

interface PlaceDevService {
    fun migration(): List<Place>

    fun createConfig(req: CreateConfigReq): Response

}