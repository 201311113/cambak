package com.example.cambak.cambak.domains.campingcar_reservation.controller

import com.example.cambak.cambak.common.util.BadRequestException
import com.example.cambak.cambak.common.util.OK
import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.common.util.ServiceProvider
import com.example.cambak.cambak.domains.campingcar_reservation.model.CampingCarReservationDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/camping-car/reservation")
@Api(tags = ["[CampingCar Reservation] 캠핑카 예약 관련 API"])
class CampingCarReservationController {

    @Autowired
    lateinit var serviceProvider: ServiceProvider

    @ApiOperation(
        value = "캠핑카 예약 등록",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PostMapping("/create")
    fun create(
        @RequestBody req: CampingCarReservationDto.CreateReservationReq
    ):CampingCarReservationDto.CreateReservationRes{
        try {
            return serviceProvider.campingCarReservationService.create(req)
        }catch (e: BadRequestException){
            return CampingCarReservationDto.CreateReservationRes(e.code)
        }

    }

    @GetMapping("/test")
    fun test(

    ):Response{
        return Response(OK)
    }

    @ApiOperation(
        value = "캠핑카 예약 수정",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PutMapping("/update")
    fun update(
        @RequestBody req: CampingCarReservationDto.UpdateReservationReq
    ):CampingCarReservationDto.UpdateReservationRes{
        try {
            return serviceProvider.campingCarReservationService.update(req)
        }catch (e: BadRequestException){
            return CampingCarReservationDto.UpdateReservationRes(e.code)
        }
    }

    @ApiOperation(
        value = "캠핑카 예약 리스트업",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/list")
    fun getList(

    ):CampingCarReservationDto.GetReservationListRes{
        try {
            return serviceProvider.campingCarReservationService.getList()
        }catch (e: BadRequestException){
            return CampingCarReservationDto.GetReservationListRes(e.code)
        }

    }

    @ApiOperation(
        value = "캠핑카 예약 상세정보",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @GetMapping("/{reservationId}")
    fun getDetail(
        @PathVariable reservationId: String
    ):CampingCarReservationDto.GetReservationDetailRes{
        try {
            return serviceProvider.campingCarReservationService.getDetail(reservationId)
        }catch (e: BadRequestException){
            return CampingCarReservationDto.GetReservationDetailRes(e.code)
        }

    }

    @ApiOperation(
        value = "캠핑카 예약 취소",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @PatchMapping("/cancel/{reservationId}")
    fun cancel(
        @PathVariable reservationId: String
    ):CampingCarReservationDto.CancelReservationRes{
        try {
            return serviceProvider.campingCarReservationService.cancel()
        }catch (e: BadRequestException){
            return CampingCarReservationDto.CancelReservationRes(e.code)
        }
    }

    @ApiOperation(
        value = "캠핑카 예약 삭제",
        notes = """
            code 0 : 성공
            code -1: 알 수 없는 오류
        """
    )
    @DeleteMapping("/{reservationId}")
    fun delete(
        @PathVariable reservationId: String
    ):CampingCarReservationDto.DeleteReservationRes{
        try {
            return serviceProvider.campingCarReservationService.delete()
        }catch (e: BadRequestException){
            return CampingCarReservationDto.DeleteReservationRes(e.code)
        }
    }
}