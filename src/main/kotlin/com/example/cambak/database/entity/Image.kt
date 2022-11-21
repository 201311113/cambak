package com.example.cambak.database.entity

import com.example.cambak.database.BaseEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "image_type")
class Image (
    @Id
    val url: String,

    var isMain: Boolean,
    var created: LocalDateTime = LocalDateTime.now(),
    var seq: Long,
    var associatedEntityId: String,
    @Enumerated(value = EnumType.STRING)
    var type: ImageType
) {

}
enum class ImageType(text: String){
    CAMPSITE_BASIC("캠핑장 기본이미지"),CAMPSITE_LAYOUT("캠핑장 배치도")
}