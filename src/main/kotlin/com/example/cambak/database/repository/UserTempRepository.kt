package com.example.cambak.database.repository

import com.example.cambak.database.entity.UserTemp
import org.springframework.data.jpa.repository.JpaRepository

interface UserTempRepository: JpaRepository<UserTemp,Long> {

}