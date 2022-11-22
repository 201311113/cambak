package com.example.cambak.database.repository.place

import com.example.cambak.database.entity.place.Carpark
import org.springframework.data.jpa.repository.JpaRepository

interface CarparkRepository: JpaRepository<Carpark, String> {
    fun findAllByActive(active: Boolean = true): List<Carpark>?
    fun findByIdAndActive(id: String,active: Boolean = true): Carpark?
}