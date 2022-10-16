package com.example.cambak.database.repository.place

import com.example.cambak.database.entity.place.Place
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceRepository: JpaRepository<Place, String> {
    fun findByIdAndActive(id: String, active: Boolean = true): Place?
    fun findAllByActive(active: Boolean = true): List<Place>?
}