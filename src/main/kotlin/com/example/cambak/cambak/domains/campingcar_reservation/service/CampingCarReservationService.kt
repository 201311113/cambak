package com.example.cambak.cambak.domains.campingcar_reservation.service

import com.example.cambak.cambak.domains.campingcar_reservation.model.CampingCarReservationDto

interface CampingCarReservationService {
    fun create(
        req: CampingCarReservationDto.CreateReservationReq
    ):CampingCarReservationDto.CreateReservationRes

    fun update(
        req: CampingCarReservationDto.UpdateReservationReq
    ):CampingCarReservationDto.UpdateReservationRes

    fun getList(

    ): CampingCarReservationDto.GetReservationListRes

    fun getDetail(
        reservationId: String
    ): CampingCarReservationDto.GetReservationDetailRes

    fun cancel(

    ):CampingCarReservationDto.CancelReservationRes

    fun delete(

    ):CampingCarReservationDto.DeleteReservationRes
}