package com.example.cambak.database.entity.campingcar

import com.example.cambak.database.entity.Image
import java.time.LocalDateTime
import javax.persistence.Entity

@Entity
class CampingCarImage(
    url: String,
    isMain: Boolean = false,
    created: LocalDateTime = LocalDateTime.now(),
    seq: Long = -1,
    associatedEntityId: String

):Image(
    url,
    isMain,
    created,
    seq,
    associatedEntityId
) {
}