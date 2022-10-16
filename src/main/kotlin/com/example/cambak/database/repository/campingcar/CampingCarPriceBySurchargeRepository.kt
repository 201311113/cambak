package com.example.cambak.database.repository.campingcar

import com.example.cambak.database.entity.CampingCar
import com.example.cambak.database.entity.campingcar.CampingCarPriceBySurcharge
import org.springframework.data.jpa.repository.JpaRepository

interface CampingCarPriceBySurchargeRepository: JpaRepository<CampingCarPriceBySurcharge,String> {
    fun findAllByCampingCar(campingCar: CampingCar): List<CampingCarPriceBySurcharge>?
}