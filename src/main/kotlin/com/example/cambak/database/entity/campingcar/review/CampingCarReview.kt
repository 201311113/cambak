package com.example.cambak.database.entity.campingcar.review

import com.example.cambak.database.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Lob

@Entity
class CampingCarReview (
    @Column(length = 200)
    val title: String,
    @Lob
    val content: String
):BaseEntity()