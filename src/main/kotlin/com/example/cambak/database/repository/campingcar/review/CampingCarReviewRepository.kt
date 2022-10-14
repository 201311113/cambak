package com.example.cambak.database.repository.campingcar.review

import com.example.cambak.database.entity.campingcar.review.CampingCarReview
import org.springframework.data.jpa.repository.JpaRepository

interface CampingCarReviewRepository: JpaRepository<CampingCarReview, String> {
    fun findByIdAndActive(id: String, active: Boolean = true): CampingCarReview?
    fun findAllByActive(active: Boolean = true): List<CampingCarReview>?
}