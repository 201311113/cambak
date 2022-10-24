package com.example.cambak.database.entity

import java.time.LocalDateTime
import javax.persistence.Id

class UserTemp (
    @Id
    var id: Long,
    var email: String?,
    var nickname: String,
    var profileImageUrl: String?,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
    var bio: String?,
    var instagram: String?,
    var blog: String?,
    var youtube: String?,
)