package com.example.cambak.cambak.domains.campingcar.service.impl

import com.example.cambak.cambak.common.util.*
import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto
import com.example.cambak.cambak.domains.campingcar.model.CampingCarFilterType
import com.example.cambak.cambak.domains.campingcar.service.CampingCarService
import com.example.cambak.database.entity.CampingCar
import com.example.cambak.database.entity.User
import com.example.cambak.database.entity.campingcar.CampingCarAdditionalOptions
import com.example.cambak.database.entity.campingcar.CampingCarConfigMapping
import com.example.cambak.database.entity.campingcar.CampingCarPriceBySeason
import com.example.cambak.database.entity.campingcar.CampingCarPriceBySurcharge
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.regex.Pattern
import javax.transaction.Transactional
import kotlin.jvm.Throws

@Service
class CampingCarServiceImpl(
    var repo: RepositoryProvider,
): CampingCarService {

    @Transactional
    override fun enroll(
        req: CampingCarDto.EnrollCampingCarReq
    ): CampingCarDto.EnrollCampingCarRes {

        //user validate
        val context = SecurityContextHolder.getContext()

        val user = repo.userRepository.findByEmailAndActive(context.authentication.name)
            ?: return CampingCarDto.EnrollCampingCarRes(USER_NOT_FOUND)

        if(!user.isVendor) return CampingCarDto.EnrollCampingCarRes(USER_IS_NOT_VENDOR)


        val mobileNoParsed: String
        try {
            mobileNoParsed = CommonUtils.mobileNoParsing(req.mobileNo)
        }catch (e: Exception){
            return CampingCarDto.EnrollCampingCarRes(MOBILE_NO_INVALID)
        }
        try{
            validateTime(req.rentalTime)
            validateTime(req.returnTime)
        }catch (e: Exception){
            return CampingCarDto.EnrollCampingCarRes(RENTAL_TIME_INVALID)
        }


        val newCampingCar = CampingCar(
            user = user ,
            productName = req.productName,
            oneLineDescription = req.oneLineDescription,
            description = req.description,
            mobileNo = mobileNoParsed, //validate
            address = req.address,
            websiteAddress = req.websiteAddress,
            region = req.region,
            isDeliveryPossible = req.isDeliveryPossible,
            isPetPossible = req.isPetPossible,
            passengersNumber = req.passengersNumber,
            sleepPossibleNumber = req.sleepPossibleNumber,
            isParkingPossible = req.isParkingPossible,
            isEquipmentProvide = req.isEquipmentProvide,
            rentalTime = req.rentalTime,    //validate
            returnTime = req.returnTime,    //validate
            driverAgeLimit = req.driverAgeLimit,
            driverLicense = req.driverLicense,
            drivingExperience = req.drivingExperience,
            weekdayPrice = req.weekdayPrice,
            weekendPrice = req.weekendPrice,
            weekdayPriceByOnNight = req.weekdayPriceByOnNight,
            weekendPriceByOnNight = req.weekendPriceByOnNight,
            discountPercentByTwoDays = req.discountPercentByTwoDays,
            discountPercentByThreeDays = req.discountPercentByThreeDays,
            possibleRentalDaysMin = req.possibleRentalDaysMin,
            possibleRentalDaysMax = req.possibleRentalDaysMax,
        )


        //Create DB logic
        repo.campingCarRepository.save(newCampingCar)
        //TODO: query 최적화 필요

        req.basicConfigList?.stream()?.map {
            CampingCarConfigMapping(
                campingCar = newCampingCar,
                campingCarConfig = repo.campingCarConfigRepository.findByConfigType(it)?: throw BadRequestException(CONFIG_TYPE_NOT_FOUND)
            )
        }?.toList()?.let { repo.campingCarConfigMappingRepository.saveAll(it) }


        req.priceBySeasonList?.stream()?.map {
            CampingCarPriceBySeason(
                campingCar = newCampingCar,
                startDay = it.startDay,
                endDay = it.endDay,
                weekDayPrice = it.weekDayPrice,
                weekendPrice = it.weekendPrice
            )
        }?.toList()?.let { repo.campingCarPriceBySeasonRepository.saveAll(it) }

        req.priceBySurchargeList?.stream()?.map {
            CampingCarPriceBySurcharge(
                campingCar = newCampingCar,
                serviceName = it.serviceName,
                payMethod = it.payMethod,
                price = it.price
            )
        }?.toList()?.let { repo.campingCarPriceBySurchargeRepository.saveAll(it) }

        req.additionalOptionList?.stream()?.map {
            CampingCarAdditionalOptions(
                campingCar = newCampingCar,
                optionName = it.optionName,
                price = it.price
            )
        }?.toList()?.let { repo.campingCarAdditionalOptionsRepository.saveAll(it) }


        return CampingCarDto.EnrollCampingCarRes(
            OK,
            newCampingCar.id
        )
    }

    override fun uploadImages(
        campingCarId: String, images: List<MultipartFile>
    ): CampingCarDto.CampingCarImageRes {

        return CampingCarDto.CampingCarImageRes(OK)
    }

    override fun delete(campingCarId: String): CampingCarDto.DeleteCampingCarRes {
        val campingCar = repo.campingCarRepository.findByIdAndActive(campingCarId)
            ?: return CampingCarDto.DeleteCampingCarRes(CAMPING_CAR_NOT_FOUND)

        //TODO: delete 쿼리 한번에 하는 것이나 제약조건 거는거 찾고 블로깅
        repo.campingCarRepository.delete(campingCar)

        return CampingCarDto.DeleteCampingCarRes(OK)
    }

    override fun listUp(
        filterTypeList: List<CampingCarFilterType>?
    ): CampingCarDto.GetCampingCarListRes {
        //TODO: 기획 - 어떤거 가져올 지

        val campingCarList = repo.campingCarRepository.findAll()
            .stream()
            .map {
                CampingCarDto.GetCampingCarListRes.CampingCarInfo(
                    id = it.id!!,
                    productName = it.productName,
                    description = it.description,
                )
            }
            .toList()

        return CampingCarDto.GetCampingCarListRes(
            OK,
            campingCarList
        )
    }

    override fun getDetail(campingCarId: String): CampingCarDto.GetCampingCarDetailRes {
        val campingCar = repo.campingCarRepository.findByIdAndActive(campingCarId)
            ?: return CampingCarDto.GetCampingCarDetailRes(CAMPING_CAR_NOT_FOUND)

        return CampingCarDto.GetCampingCarDetailRes(
            OK,
            CampingCarDto.GetCampingCarDetailRes.CampingCarDetail(
                id = campingCar.id!!,
                productName = campingCar.productName,
                oneLineDescription = campingCar.oneLineDescription,
                description = campingCar.description
            )
        )
    }


    @Throws(Exception::class)
    private fun validateTime(time: String): Boolean{
        val pattern = Pattern.compile("\\d{2}:\\d{2}")
        val matchar = pattern.matcher(time)
        if(!matchar.matches()) throw Exception("Invalid mobildNo")
        return true
    }

    override fun configListUp(): CampingCarDto.ConfigListUpRes {
        val configList = repo.campingCarConfigRepository.findAllByConfigType("basic")!!
            .stream()
            .map {
                CampingCarDto.ConfigListUpRes.Config(
                    it.campingCarConfigKey,
                    it.campingCarConfigName,
                )
            }.toList()
        return CampingCarDto.ConfigListUpRes(
            OK,
            configList
        )

    }
}