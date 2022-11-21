package com.example.cambak.database.entity.place

import javax.persistence.*

@Entity
class PlaceConfig (
    @Id
    @Column(nullable = false)
    val placeConfigKey: String,

    @Column(nullable = false)
    val placeConfigName: String,

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    val placeConfigType: ConfigType
)

enum class ConfigType(text: String){
    HASHTAG("해시태그"),FACILITIES("기본시설"), AMENITIES("부대시설"), RULES("이용규칙")
}