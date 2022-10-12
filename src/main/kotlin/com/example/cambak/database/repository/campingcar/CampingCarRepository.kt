package com.example.cambak.database.repository.campingcar

import com.example.cambak.database.entity.CampingCar
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CampingCarRepository: JpaRepository<CampingCar, String> {
    fun findByIdAndActive(id: String, active: Boolean = true): CampingCar?
    @Query("""
        
    """)
    fun deleteByIdAndActive(id:String, active: Boolean = true)
}