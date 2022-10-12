package com.example.cambak.database.repository.campingcar

import com.example.cambak.database.entity.campingcar.CampingCarConfigMapping
import org.springframework.data.jpa.repository.JpaRepository

interface CampingCarConfigMappingRepository: JpaRepository<CampingCarConfigMapping,String> {
}