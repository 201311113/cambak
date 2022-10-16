package com.example.cambak.database.repository.place

import com.example.cambak.database.entity.place.CampSite
import org.springframework.data.jpa.repository.JpaRepository

interface CampsiteRepository: JpaRepository<CampSite, String> {
    fun findByIdAndActive(id: String, active: Boolean = true): CampSite
}