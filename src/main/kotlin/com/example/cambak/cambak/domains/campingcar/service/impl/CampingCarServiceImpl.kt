package com.example.cambak.cambak.domains.campingcar.service.impl

import com.example.cambak.cambak.common.util.*
import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto
import com.example.cambak.cambak.domains.campingcar.service.CampingCarService
import com.example.cambak.database.entity.CampingCar
import com.example.cambak.database.entity.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
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

        println("[[[[[[[[[[[[[[[[[[--------- 캠핑카 등록 서비스---------]]]]]]]]]]]]]]]]]")
        //user validate
        val context = SecurityContextHolder.getContext()
        println(context.authentication.name)
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
            //TODO:season config
            //TODO:basic config
            //TODO:surcharge config
            //TODO:add options config
        )
        try{
            if(!req.basicConfigList.isNullOrEmpty()) validateBasicConfig(req.basicConfigList!!)
        }catch (e: Exception){
            return CampingCarDto.EnrollCampingCarRes(BASIC_CONFIG_INVALID)
        }


        return CampingCarDto.EnrollCampingCarRes(OK)

    }

    @Throws(Exception::class)
    private fun validateBasicConfig(
        configList: List<String>
    ):Boolean{
        //TODO: validate query 짜기
//        repo.campingCarConfigRepository.existsByConfigList(configList)
        throw Exception("Invalid basic config")
    }

    @Throws(Exception::class)
    private fun validateTime(time: String): Boolean{
        val pattern = Pattern.compile("\\d{2}:\\d{2}")
        val matchar = pattern.matcher(time)
        if(!matchar.matches()) throw Exception("Invalid mobildNo")
        return true
    }

    override fun configListUp(): CampingCarDto.ConfigListUpRes {
        println("[[[[[[[[[[[[[[[[[[--------- 부가기능 리스트 ---------]]]]]]]]]]]]]]]]]")
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