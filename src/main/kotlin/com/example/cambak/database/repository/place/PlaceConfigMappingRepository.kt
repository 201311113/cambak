package com.example.cambak.database.repository.place

import com.example.cambak.database.entity.place.Place
import com.example.cambak.database.entity.place.PlaceConfigMapping
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import javax.transaction.Transactional

interface PlaceConfigMappingRepository: JpaRepository<PlaceConfigMapping, String> {


    @Query(
        """
           delete from PlaceConfigMapping pcm where pcm.place = :place
        """
    )

    @Transactional
    @Modifying
    fun deleteAllByPlace(place: Place)
}