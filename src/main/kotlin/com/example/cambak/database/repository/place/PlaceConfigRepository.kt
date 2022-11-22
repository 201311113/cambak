package com.example.cambak.database.repository.place

import com.example.cambak.database.entity.place.ConfigType
import com.example.cambak.database.entity.place.PlaceConfig
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceConfigRepository: JpaRepository<PlaceConfig, String> {
    fun findByPlaceConfigKeyAndPlaceConfigType(placeConfigKey: String, placeConfigType: ConfigType): PlaceConfig?
    fun findAllByPlaceConfigType(placeConfigType: ConfigType):List<PlaceConfig>?
}