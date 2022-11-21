package com.example.cambak.database.entity.place

import com.example.cambak.database.entity.place.key.PlaceConfigMappingKey
import javax.persistence.*

@Entity
@IdClass(PlaceConfigMappingKey::class)
class PlaceConfigMapping (
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    val place: Place,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_config_key", nullable = false)
    val placeConfig: PlaceConfig
)