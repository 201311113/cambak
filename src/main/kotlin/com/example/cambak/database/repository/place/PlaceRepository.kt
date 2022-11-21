package com.example.cambak.database.repository.place

import com.example.cambak.database.entity.place.Place
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PlaceRepository: JpaRepository<Place, String> {

    fun findByIdAndActive(id: String, active: Boolean = true): Place?

    @Query(
        """
            select distinct p from Place p join p.placeConfigList where p.active = :active 
        """
    )
    fun findAllByActive(active: Boolean = true): List<Place>?
}