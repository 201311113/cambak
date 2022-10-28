package com.example.cambak.cambak.domains.campingcar.service.impl

import com.example.cambak.cambak.common.util.*
import com.example.cambak.cambak.domains.campingcar.model.CampingCarDto
import com.example.cambak.cambak.domains.campingcar.model.CampingCarFilterType
import com.example.cambak.cambak.domains.campingcar.service.CampingCarService
import com.example.cambak.database.entity.CampingCar
import com.example.cambak.database.entity.User
import com.example.cambak.database.entity.campingcar.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.regex.Pattern
import javax.transaction.Transactional
import kotlin.jvm.Throws
import kotlin.streams.toList

@Service
class CampingCarServiceImpl(
    var repo: RepositoryProvider,
    var awsS3Service: AmazonS3Service,
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

    override fun update(req: CampingCarDto.UpdateCampingCarReq): CampingCarDto.UpdateCampingCarRes {
        //validate
        val campingCar = repo.campingCarRepository.findByIdAndActive(req.id)
            ?: return CampingCarDto.UpdateCampingCarRes(CAMPING_CAR_NOT_FOUND)

        val context = SecurityContextHolder.getContext()

        val user = repo.userRepository.findByEmailAndActive(context.authentication.name)
            ?: return CampingCarDto.UpdateCampingCarRes(USER_NOT_FOUND)

        if(campingCar.user.id != user.id) return CampingCarDto.UpdateCampingCarRes(USER_IS_INCONSISTENT_WITH_CAMPINGCAR_OWNER)

        val mobileNoParsed: String
        try {
            mobileNoParsed = req.mobileNo?.let { CommonUtils.mobileNoParsing(it) }.toString()
        }catch (e: Exception){
            return CampingCarDto.UpdateCampingCarRes(MOBILE_NO_INVALID)
        }
        try{
            req.rentalTime?.let { validateTime(it) }
            req.returnTime?.let { validateTime(it) }
        }catch (e: Exception){
            return CampingCarDto.UpdateCampingCarRes(RENTAL_TIME_INVALID)
        }



        //update
        req.productName?.let { campingCar.productName = req.productName!! }
        req.oneLineDescription?.let { campingCar.oneLineDescription = req.oneLineDescription!! }
        req.description?.let { campingCar.description = req.description!! }
        req.mobileNo?.let { campingCar.mobileNo = req.mobileNo!! }
        req.address?.let{campingCar.address  =req.address!!}
        req.websiteAddress?.let { campingCar.websiteAddress = req.websiteAddress!! }
        req.region?.let { campingCar.region = req.region!! }
        req.isDeliveryPossible?.let { campingCar.isDeliveryPossible = req.isDeliveryPossible!! }
        req.isPetPossible?.let { campingCar.isPetPossible = req.isPetPossible!! }
        req.passengersNumber?.let { campingCar.passengersNumber = req.passengersNumber!! }
        req.sleepPossibleNumber?.let { campingCar.sleepPossibleNumber = req.sleepPossibleNumber!! }
        req.isParkingPossible?.let { campingCar.isParkingPossible = req.isParkingPossible!! }
        req.isEquipmentProvide?.let { campingCar.isEquipmentProvide = req.isEquipmentProvide!! }
        req.rentalTime?.let { campingCar.rentalTime = req.rentalTime!! }
        req.returnTime?.let { campingCar.returnTime = req.returnTime!! }
        req.driverAgeLimit?.let { campingCar.driverAgeLimit = req.driverAgeLimit!! }
        req.driverLicense?.let { campingCar.driverLicense = req.driverLicense!! }
        req.drivingExperience?.let { campingCar.drivingExperience = req.drivingExperience!! }
        req.weekdayPrice?.let { campingCar.weekdayPrice = req.weekdayPrice!! }
        req.weekendPrice?.let { campingCar.weekendPrice = req.weekendPrice!! }
        req.weekdayPriceByOnNight?.let { campingCar.weekdayPriceByOnNight = req.weekdayPriceByOnNight!! }
        req.weekendPriceByOnNight?.let { campingCar.weekendPriceByOnNight = req.weekendPriceByOnNight!! }
        req.discountPercentByTwoDays?.let { campingCar.discountPercentByTwoDays = req.discountPercentByTwoDays!! }
        req.discountPercentByThreeDays?.let { campingCar.discountPercentByThreeDays = req.discountPercentByThreeDays!! }
        req.possibleRentalDaysMax?.let { campingCar.possibleRentalDaysMax = req.possibleRentalDaysMax!! }
        req.possibleRentalDaysMin?.let { campingCar.possibleRentalDaysMin = req.possibleRentalDaysMin!! }

        //TODO: [basic, season, surcharge, options] update logic

        repo.campingCarRepository.save(campingCar)

        return CampingCarDto.UpdateCampingCarRes(
            OK,
            campingCar.id
            )
    }

    override fun uploadImages(
        campingCarId: String,
        images: List<MultipartFile>?,
        deleteImageIds: String?
    ): CampingCarDto.CampingCarImageRes {
        //validate

        val context = SecurityContextHolder.getContext()
        val user = repo.userRepository.findByEmailAndActive(context.authentication.name)
            ?: return CampingCarDto.CampingCarImageRes(USER_NOT_FOUND)

        val campingCar = repo.campingCarRepository.findByIdAndActive(campingCarId)
            ?: return CampingCarDto.CampingCarImageRes(CAMPING_CAR_NOT_FOUND)

        if(campingCar.user.id != user.id) return CampingCarDto.CampingCarImageRes(USER_IS_INCONSISTENT_WITH_CAMPINGCAR_OWNER)

        val oldImageList = repo.campingCarImageRepository.findAllByAssociatedEntityId(campingCarId)

        images?.let {
            uploadImages(oldImageList,campingCarId,images)
        }

        deleteImageIds?.let {
            deleteImages(deleteImageIds)
        }


        return CampingCarDto.CampingCarImageRes(OK)
    }
    @Throws(BadRequestException::class)
    private fun uploadImages(
        oldImageList: List<CampingCarImage>?,
        campingCarId: String,
        images: List<MultipartFile>?
    ){
            repo.campingCarImageRepository.saveAll(
                images!!.map {
                    val id = CommonUtils.uuid()

                    val s3ImageUrl = awsS3Service.uploadImageS3(
                        it,
                        id
                    )
                    CampingCarImage(
                        url = s3ImageUrl,
                        associatedEntityId = campingCarId
                    )
                }
            )


    }

    @Throws(BadRequestException::class)
    private fun deleteImages(
        deleteImageIds: String?
    ){
        try {
            val deleteImageList = deleteImageIds!!.split(",")

            repo.campingCarImageRepository.deleteAllInIds(deleteImageList)
            awsS3Service.deleteImagesS3(deleteImageList)
        }catch (e: Exception){
            throw BadRequestException(IMAGE_DELETE_FAIL)
        }

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

        //TODO: filtering
        val campingCarList = repo.campingCarRepository.findAll()


        return CampingCarDto.GetCampingCarListRes(
            OK,
            buildCampingCar(campingCarList),

        )
    }

    private fun buildCampingCar(
        campingCarList: List<CampingCar>
    ):List<CampingCarDto.GetCampingCarListRes.CampingCarInfo>{

        val buildCampingCarInfoList = campingCarList.stream().map {
            CampingCarDto.GetCampingCarListRes.CampingCarInfo(
                id = it.id!!,
                productName = it.productName,
                description = it.description,
                basicConfigList = repo.campingCarConfigMappingRepository.findAllByCampingCar(it)
                    ?.stream()
                    !!.map {
                        CampingCarDto.GetCampingCarListRes.CampingCarInfo.BasicConfig(
                            configName = it.campingCarConfig.campingCarConfigName
                        )
                    }.toList(),
                priceBySeasonList = repo.campingCarPriceBySeasonRepository.findAllByCampingCar(it)
                    ?.stream()
                    !!.map {
                        CampingCarDto.GetCampingCarListRes.CampingCarInfo.PriceBySeason(
                            startDay = it.startDay,
                            endDay = it.endDay,
                            weekDayPrice = it.weekDayPrice,
                            weekendPrice = it.weekendPrice,
                        )
                    }.toList(),
                priceBySurcharge = repo.campingCarPriceBySurchargeRepository.findAllByCampingCar(it)
                    ?.stream()
                    !!.map {
                        CampingCarDto.GetCampingCarListRes.CampingCarInfo.PriceBySurcharge(
                            serviceName = it.serviceName,
                            payMethod = it.payMethod,
                            price = it.price,
                        )
                    }.toList()
            )
        }.toList()
        
        return buildCampingCarInfoList
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