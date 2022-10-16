package com.example.cambak.database.entity.campingcar

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class CampingCarConfig (
    @Id
    @Column(nullable = false)
    val campingCarConfigKey: String,

    @Column(nullable = false)
    val campingCarConfigName: String,

    @Column(nullable = false)
    val configType: String
)