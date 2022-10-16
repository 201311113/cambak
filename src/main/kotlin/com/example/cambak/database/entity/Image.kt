package com.example.cambak.database.entity

import com.example.cambak.database.BaseEntity
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Image (
    @Id
    val url: String,

    var isMain: Boolean,
    var seq: Long,
)