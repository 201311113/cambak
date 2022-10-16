package com.example.cambak.database.entity.campingcar.review

import com.example.cambak.database.BaseEntity
import com.example.cambak.database.entity.CampingCar
import com.example.cambak.database.entity.Image
import com.example.cambak.database.entity.User
import javax.persistence.*

@Entity
class CampingCarReview (


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camping_car_id", nullable = false)
    val campingCar: CampingCar,

    @Column(length = 200)
    var title: String,
    @Lob
    var content: String,
//    var images: List<Image>,
//    var rate: Long, //평점 점수?
):BaseEntity()