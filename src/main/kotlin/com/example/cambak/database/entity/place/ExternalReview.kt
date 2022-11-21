package com.example.cambak.database.entity

import com.example.cambak.database.BaseEntity
import com.example.cambak.database.entity.place.Place
import javax.persistence.*

//유튜브 url, 블로그 url
@Entity
class ExternalReview (

    val url: String,
    @Enumerated(value = EnumType.STRING)
    val type: Type,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    val place: Place
):BaseEntity()
enum class Type{
    BLOG, YOUTUBE
}