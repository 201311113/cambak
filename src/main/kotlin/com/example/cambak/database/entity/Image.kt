package com.example.cambak.database.entity

import com.example.cambak.database.BaseEntity
import java.time.LocalDateTime
import javax.persistence.DiscriminatorColumn
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

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
) {

}