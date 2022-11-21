package com.example.cambak.database.repository

import com.example.cambak.database.entity.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface ImageRepository: JpaRepository<Image, String > {
    fun findAllByAssociatedEntityId(associatedEntityId: String): List<Image>?


    @Query(
        """
            delete from Image i where i.url in :deleteIdList
        """
    )
    @Modifying
    fun deleteAllInIds(deleteIdList: List<String>)
}