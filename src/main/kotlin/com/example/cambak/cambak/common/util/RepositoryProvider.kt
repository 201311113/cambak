package com.example.cambak.cambak.common.util

import com.example.cambak.database.repository.UserRepository
import com.example.cambak.database.repository.campingcar.*
import com.example.cambak.database.repository.campingcar.reservation.CampingCarReservationRepository
import com.example.cambak.database.repository.campingcar.review.CampingCarReviewRepository
import com.example.cambak.database.repository.place.CampsiteRepository
import com.example.cambak.database.repository.place.PlaceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RepositoryProvider {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var campingCarRepository: CampingCarRepository

    @Autowired
    lateinit var campingCarConfigRepository: CampingCarConfigRepository

    @Autowired
    lateinit var campingCarConfigMappingRepository: CampingCarConfigMappingRepository

    @Autowired
    lateinit var campingCarAdditionalOptionsRepository: CampingCarAdditionalOptionsRepository

    @Autowired
    lateinit var campingCarPriceBySeasonRepository: CampingCarPriceBySeasonRepository

    @Autowired
    lateinit var campingCarPriceBySurchargeRepository: CampingCarPriceBySurchargeRepository

    @Autowired
    lateinit var campingCarReservationRepository: CampingCarReservationRepository

    @Autowired
    lateinit var campingCarReviewRepository: CampingCarReviewRepository

    @Autowired
    lateinit var placeRepository: PlaceRepository

    @Autowired
    lateinit var campsiteRepository: CampsiteRepository

}