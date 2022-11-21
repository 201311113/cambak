package com.example.cambak.database.repository.place

import com.example.cambak.database.entity.place.Campsite
import org.springframework.data.jpa.repository.JpaRepository

interface CampsiteRepository: JpaRepository<Campsite, String> {
    fun findByIdAndActive(id: String, active: Boolean = true): Campsite?
    fun findAllByActive(active: Boolean = true): List<Campsite>?
}