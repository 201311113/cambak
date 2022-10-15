package com.example.cambak.cambak.domains.campingcar_reservation.service.impl

import com.example.cambak.cambak.common.util.*
import com.example.cambak.cambak.domains.campingcar_reservation.model.CampingCarReservationDto
import com.example.cambak.cambak.domains.campingcar_reservation.service.CampingCarReservationService
import com.example.cambak.cambak.domains.campingcar_review.model.CampingCarReviewDto
import com.example.cambak.database.entity.CampingCar
import com.example.cambak.database.entity.campingcar.reservation.CampingCarReservation
import com.example.cambak.database.entity.campingcar.reservation.ReservationStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class CampingCarReservationServiceImpl(
    var repo: RepositoryProvider
): CampingCarReservationService {
    override fun create(req: CampingCarReservationDto.CreateReservationReq): CampingCarReservationDto.CreateReservationRes {

        //validate
        val context = SecurityContextHolder.getContext()
        val user = repo.userRepository.findByEmailAndActive(context.authentication.name)
            ?: return CampingCarReservationDto.CreateReservationRes(USER_NOT_FOUND)
        val campingCar = repo.campingCarRepository.findByIdAndActive(req.campingCarId)
            ?: return CampingCarReservationDto.CreateReservationRes(CAMPING_CAR_NOT_FOUND)

//        val campingCarBasicConfigList = repo.campingCarConfigMappingRepository.findAllByCampingCar(campingCar)

        val newReservation = CampingCarReservation(
            user = user,
            campingCar = campingCar,
            rentalTime = req.rentalTime,
            returnTime = req.returnTime,
            basicConfigList = buildBasicConfig(req.basicConfigList),
            status = ReservationStatus.INIT,
        )

        repo.campingCarReservationRepository.save(newReservation)

        return CampingCarReservationDto.CreateReservationRes(
            OK,
            newReservation.id
        )
    }

    private fun buildBasicConfig(
        basicConfigList: List<String>,
//        campingCarConfigList: List<String>//campingcar가 선택된 config list(ccc_maping) 가져오기
    ):String{
        //TODO: validate basicConfig
//        val configList = repo.campingCarConfigRepository.findAllByConfigType("basic")

        return basicConfigList.joinToString(",")
    }

    override fun update(req: CampingCarReservationDto.UpdateReservationReq): CampingCarReservationDto.UpdateReservationRes {

        //validate
        val context = SecurityContextHolder.getContext()
        val user = repo.userRepository.findByEmailAndActive(context.authentication.name)
            ?: return CampingCarReservationDto.UpdateReservationRes(USER_NOT_FOUND)

        val reservation = repo.campingCarReservationRepository.findByIdAndActive(req.reservationId)
            ?: return CampingCarReservationDto.UpdateReservationRes(CAMPING_CAR_RESERVATION_NOT_FOUND)

        if(user.id != reservation.user.id) return CampingCarReservationDto.UpdateReservationRes(USER_IS_INCONSISTENT_WITH_CAMPING_CAR_RESERVATION_OWNER)

        req.rentalTime?.let { reservation.rentalTime = req.rentalTime!! }
        req.returnTime?.let { reservation.returnTime = req.returnTime!! }
        req.status?.let { reservation.status = req.status!! }
        req.basicConfigList?.let { reservation.basicConfigList = buildBasicConfig(req.basicConfigList!!) }

        repo.campingCarReservationRepository.save(reservation)

        return CampingCarReservationDto.UpdateReservationRes(
            OK,
            reservation.id
        )
    }

    override fun getList(): CampingCarReservationDto.GetReservationListRes {
        TODO("Not yet implemented")
    }

    override fun getDetail(): CampingCarReservationDto.GetReservationDetailRes {
        TODO("Not yet implemented")
    }

    override fun cancel(): CampingCarReservationDto.CancelReservationRes {
        TODO("Not yet implemented")
    }

    override fun delete(): CampingCarReservationDto.DeleteReservationRes {
        TODO("Not yet implemented")
    }

}