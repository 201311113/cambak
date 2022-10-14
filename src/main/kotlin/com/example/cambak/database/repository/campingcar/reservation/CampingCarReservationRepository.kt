package com.example.cambak.database.repository.campingcar.reservation

import com.example.cambak.database.entity.campingcar.reservation.CampingCarReservation
import org.springframework.data.jpa.repository.JpaRepository

interface CampingCarReservationRepository: JpaRepository<CampingCarReservation, String> {
}