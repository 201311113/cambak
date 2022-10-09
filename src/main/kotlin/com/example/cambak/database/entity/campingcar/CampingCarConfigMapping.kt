package com.example.cambak.database.entity.campingcar

import com.example.cambak.database.entity.CampingCar
import com.example.cambak.database.entity.campingcar.key.CampingCarConfigMappingKey
import javax.persistence.*

@Entity
@Table(name = "ccc_mapping")
@IdClass(CampingCarConfigMappingKey::class)
class CampingCarConfigMapping (
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camping_car_id", nullable = false)
    val campingCar: CampingCar,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camping_car_config_id", nullable = false)
    val campingCarConfig: CampingCarConfig

)