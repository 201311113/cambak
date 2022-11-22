package com.example.cambak.cambak.common.util

import com.example.cambak.cambak.domains.auth.service.AuthService
import com.example.cambak.cambak.domains.campingcar.service.CampingCarService
import com.example.cambak.cambak.domains.campingcar_reservation.service.CampingCarReservationService
import com.example.cambak.cambak.domains.campingcar_review.service.CampingCarReviewService
import com.example.cambak.cambak.domains.place.campsite.service.CampsiteService
import com.example.cambak.cambak.domains.place.carpark.service.CarparkService
import com.example.cambak.cambak.domains.place.place.service.PlaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ServiceProvider {

    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var campingcarService: CampingCarService

    @Autowired
    lateinit var campingCarReviewService: CampingCarReviewService

    @Autowired
    lateinit var campingCarReservationService: CampingCarReservationService

    @Autowired
    lateinit var placeService: PlaceService

    @Autowired
    lateinit var campsiteService: CampsiteService

    @Autowired
    lateinit var carparkService: CarparkService


}