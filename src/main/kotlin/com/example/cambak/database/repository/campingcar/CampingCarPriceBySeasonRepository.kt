package com.example.cambak.database.repository.campingcar

import com.example.cambak.database.entity.campingcar.CampingCarPriceBySeason
import org.springframework.data.jpa.repository.JpaRepository

interface CampingCarPriceBySeasonRepository: JpaRepository<CampingCarPriceBySeason,String> {
}