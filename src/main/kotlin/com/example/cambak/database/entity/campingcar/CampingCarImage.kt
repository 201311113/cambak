package com.example.cambak.database.entity.campingcar

import com.example.cambak.database.entity.Image
import com.example.cambak.database.entity.ImageType
import java.time.LocalDateTime
import javax.persistence.Entity

@Entity
class CampingCarImage(
    url: String,
    isMain: Boolean = false,
    created: LocalDateTime = LocalDateTime.now(),
    seq: Long = -1,
    associatedEntityId: String,
    type: ImageType

):Image(
    url,
    isMain,
    created,
    seq,
    associatedEntityId,
    type
) {
}