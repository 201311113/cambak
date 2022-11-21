package com.example.cambak.database.repository.place

import com.example.cambak.database.entity.ExternalReview
import com.example.cambak.database.entity.place.Place
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import javax.transaction.Transactional

interface ExternalReviewRepository: JpaRepository<ExternalReview, String> {
    @Query(
        """
            delete from ExternalReview er where er.place = :place
        """
    )
    @Modifying
    @Transactional
    fun deleteAllByPlace(place:Place)
}