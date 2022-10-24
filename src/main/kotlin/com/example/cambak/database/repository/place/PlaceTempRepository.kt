package com.example.cambak.database.repository.place

import com.example.cambak.database.entity.place.PlaceTemp
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceTempRepository: JpaRepository<PlaceTemp, Long> {

}