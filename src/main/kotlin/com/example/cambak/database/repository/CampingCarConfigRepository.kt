package com.example.cambak.database.repository

import com.example.cambak.database.entity.campingcar.CampingCarConfig
import org.springframework.data.jpa.repository.JpaRepository

interface CampingCarConfigRepository: JpaRepository<CampingCarConfig, String> {
    fun findByConfigType(configType: String): CampingCarConfig?
    fun existsByConfigType(configType: String): Boolean
    fun existsByCampingCarConfigKey(configKey: String): Boolean
    fun existsByCampingCarConfigName(configName: String): Boolean
    fun findAllByConfigType(configType: String): List<CampingCarConfig>?
}