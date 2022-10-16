package com.example.cambak.cambak.domains.campingcar_reservation.model

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.database.entity.campingcar.reservation.ReservationStatus
import java.time.LocalDateTime

class CampingCarReservationDto {
    //create
    class CreateReservationReq(
        var campingCarId: String,
        var rentalTime: String,
        var returnTime: String,
        var basicConfigList: List<String>,
        var status: ReservationStatus,
    )
    class CreateReservationRes(
        code:Int,
        var reservationId: String ?= null
    ):Response(code)
    //update
    class UpdateReservationReq(
        var reservationId: String,
        var rentalTime: String?,
        var returnTime: String?,
        var basicConfigList: List<String>?,
        var status: ReservationStatus?
    )
    class UpdateReservationRes(
        code:Int,
        var reservationId: String ?= null
    ):Response(code)
    //get list
    class GetReservationListReq(

    )
    class GetReservationListRes(
        code:Int,
        var reservationList: List<ReservationInfo> ?= null
    ):Response(code){
        class ReservationInfo(
            var id: String,
            var rentalTime: String,
            var returnTime: String,
            var basicConfigList: List<String>,
            var status: ReservationStatus,

            )
    }

    //get detail
    class GetReservationDetailReq(

    )
    class GetReservationDetailRes(
        code:Int,
        var reservationDetail: ReservationDetail ?= null
    ):Response(code){
        class ReservationDetail(
            var id: String,
            var rentalTime: String,
            var returnTime: String,
            var basicConfigList: List<String>,
            var status: ReservationStatus,
        )
    }

    //cancel
    class CancelReservationReq(

    )
    class CancelReservationRes(
        code: Int
    ):Response(code)

    //delete
    class DeleteReservationReq(

    )
    class DeleteReservationRes(
        code:Int
    ):Response(code)
}