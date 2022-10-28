package com.example.cambak.database.repository.campingcar

import com.example.cambak.database.entity.campingcar.CampingCarImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CampingCarImageRepository: JpaRepository<CampingCarImage, String> {
    fun findAllByAssociatedEntityId(associatedEntityId: String): List<CampingCarImage>?

    @Query(
        """
            delete from CampingCarImage c where c.url in :deleteIdList
        """
    )
    fun deleteAllInIds(deleteIdList: List<String>)
}