package com.example.cambak.database.repository

import com.example.cambak.database.entity.Image
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository: JpaRepository<Image, String > {

}