package com.example.cambak.cambak.domains.user.service

import com.example.cambak.database.entity.UserTemp

interface UserDevService {
    fun migration(): List<UserTemp>
}