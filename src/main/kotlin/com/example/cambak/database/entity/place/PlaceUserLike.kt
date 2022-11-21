package com.example.cambak.database.entity.place

import com.example.cambak.database.entity.User
import com.example.cambak.database.entity.place.key.PlaceUserLikeKey
import javax.persistence.*

@Entity
@IdClass(PlaceUserLikeKey::class)
class PlaceUserLike (
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    val place: Place,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
)