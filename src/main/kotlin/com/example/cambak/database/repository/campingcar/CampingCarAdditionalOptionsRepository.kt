package com.example.cambak.database.repository.campingcar

import com.example.cambak.database.entity.campingcar.CampingCarAdditionalOptions
import org.springframework.data.jpa.repository.JpaRepository

interface CampingCarAdditionalOptionsRepository: JpaRepository<CampingCarAdditionalOptions,String> {
}