package com.example.cambak.database.repository

import com.example.cambak.database.entity.SignInType
import com.example.cambak.database.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
    fun existsByEmailAndSignInTypeAndActive(email: String, singInType: SignInType, active: Boolean = true): Boolean
    fun existsByUserIdAndActive(userId: String, active: Boolean = true): Boolean
    fun findByEmail(email: String): User?

    fun findByEmailAndActive(email: String, active: Boolean = true): User?
}