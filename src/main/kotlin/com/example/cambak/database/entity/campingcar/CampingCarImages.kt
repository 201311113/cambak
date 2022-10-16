package com.example.cambak.database.entity.campingcar

import com.example.cambak.database.entity.CampingCar
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class CampingCarImages (
    @Id
    val url: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "camping_car_id")
    val campingCar: CampingCar,

    @Column(nullable = false)
    var isMain: Boolean,

    @Column(nullable = false)
    val created: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var seq: Int? = 0
)