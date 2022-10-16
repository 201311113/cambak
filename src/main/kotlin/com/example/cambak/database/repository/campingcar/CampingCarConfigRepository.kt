package com.example.cambak.database.repository.campingcar

import com.example.cambak.database.entity.campingcar.CampingCarConfig
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CampingCarConfigRepository: JpaRepository<CampingCarConfig, String> {

//    @Query("select c from CampingCarConfig c where c.campingCarConfigKey in :configList")
//    fun existsByConfigList(configList: List<String>): Boolean
    fun findByConfigType(configType: String): CampingCarConfig?
    fun existsByConfigType(configType: String): Boolean
    fun existsByCampingCarConfigKey(configKey: String): Boolean
    fun existsByCampingCarConfigName(configName: String): Boolean
    fun findAllByConfigType(configType: String): List<CampingCarConfig>?
}