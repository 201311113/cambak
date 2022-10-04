package com.example.cambak.database.repository

import com.example.cambak.database.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): User?
}