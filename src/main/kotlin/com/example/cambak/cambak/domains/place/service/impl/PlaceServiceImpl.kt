package com.example.cambak.cambak.domains.place.service.impl

import com.example.cambak.cambak.common.util.*
import com.example.cambak.cambak.domains.place.model.PlaceDto
import com.example.cambak.cambak.domains.place.service.PlaceService
import com.example.cambak.database.entity.ImageType
import com.example.cambak.database.entity.place.*
import com.mysql.cj.x.protobuf.Mysqlx.Ok
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class PlaceServiceImpl(
    var repo: RepositoryProvider
): PlaceService {
    override fun create(req: PlaceDto.CreatePlaceReq): PlaceDto.CreatePlaceRes {
//        //user validate
//
//        val context = SecurityContextHolder.getContext()
//
//        val user = repo.userRepository.findByEmailAndActive(context.authentication.name)
//            ?: return PlaceDto.CreatePlaceRes(USER_NOT_FOUND)
//
//        val newPlace = Place(
//            name = req.name,
//            address = req.address,
//            addressDetail = req.addressDetail,
//            lat = req.lat,
//            lng = req.lng
//        )
//
//        repo.placeRepository.save(newPlace)

        return PlaceDto.CreatePlaceRes(
            OK,
//            newPlace.id
        )
    }

    override fun update(req: PlaceDto.UpdatePlaceReq): PlaceDto.UpdatePlaceRes {
//        //user validate
//
//        val context = SecurityContextHolder.getContext()
//
//        val user = repo.userRepository.findByEmailAndActive(context.authentication.name)
//            ?: return PlaceDto.UpdatePlaceRes(USER_NOT_FOUND)
//
//        val place = repo.placeRepository.findByIdAndActive(req.placeId)
//            ?: return PlaceDto.UpdatePlaceRes(OK)
//
//        //update logic
//        req.address?.let { place.address = req.address!! }
//        req.addressDetail?.let { place.addressDetail = req.addressDetail!! }
//        req.name?.let { place.name = req.name!! }
//        req.lat?.let { place.lat = req.lat!! }
//        req.lng?.let { place.lng = req.lng!! }
//
//        repo.placeRepository.save(place)

        return PlaceDto.UpdatePlaceRes(
            OK,
//            place.id
        )

    }

    override fun getList(
        filterTypeList: List<String>
    ): PlaceDto.GetPlaceListRes {
        var placeList = repo.placeRepository.findAllByActive()
            ?:return PlaceDto.GetPlaceListRes(PLACE_NOT_FOUND)
        //캠핑 유형 filter
        if(filterTypeList.contains("CAMPSITE"))
            placeList = placeList.filter {
                it.getPlaceType() == PlaceType.CAMPSITE
            }
        else if(filterTypeList.contains("CARPARK"))
            placeList = placeList.filter {
                it.getPlaceType() == PlaceType.CARPARK
            }
        //configs (기본, 부대시설, 해시태그, 이용규칙) filter
        val configFilter = repo.placeConfigRepository.findAll().filter {
            filterTypeList.contains(it.placeConfigKey)
        }.map { it.placeConfigKey }

        //지역 region filter
        val regionFilter = CommonUtils.getRegions().filter {
            filterTypeList.contains(it)
        }

        //차종류 cartype filter
        val carTypeFilter = CommonUtils.getCarType().filter {
            filterTypeList.contains(it)
        }

        //환경 env filter
        val envTypeFilter = CommonUtils.getEnvType().filter {
            filterTypeList.contains(it)
        }

        placeList = placeList.
        filter {
            //config
            val placeConfigKeyList = it.placeConfigList?.map { it.placeConfig.placeConfigKey }
            configFilter.isEmpty() || placeConfigKeyList?.containsAll(configFilter) ?: false
        }.filter {
            //region
            regionFilter.isEmpty() || regionFilter.contains(it.region.toString())
        }.filter {
            carTypeFilter.isEmpty() || CommonUtils.getCarTypePossibility(carTypeFilter,it.possibleCarType.toCharArray())
        }.filter {
            //env
            envTypeFilter.isEmpty() || envTypeFilter.contains(it.environment.toString())
        }


        return PlaceDto.GetPlaceListRes(
            OK,
            buildPlaceList(placeList)
        )
    }
    private fun buildPlaceList(
        placeList: List<Place>?
    ): List<PlaceDto.GetPlaceListRes.PlaceInfo>{
        return placeList?.stream()?.map {
            PlaceDto.GetPlaceListRes.PlaceInfo(
                id = it.id!!,
                name = it.name,
                placeType = it.getPlaceType().toString(),
                address = it.address,
                lat = it.lat,
                lng = it.lng,
                possibleCarType = buildPossibleCarType(it.possibleCarType),
                configList = it.placeConfigList?.map { it.placeConfig.placeConfigKey } ?: emptyList(),
                imageUrlList = repo.imageRepository.findAllByAssociatedEntityIdAndType(it.id!!,ImageType.CAMPSITE_BASIC)?.map { it.url } ?: emptyList()
            )
        }?.toList() ?: emptyList()
    }

    private fun buildPossibleCarType(
        carTypes: String
    ): List<String>{
        val carTypeList = mutableListOf<String>()
        if(carTypes[0] == '1'){
            carTypeList.add("BASIC")
        }
        if(carTypes[1] == '1'){
            carTypeList.add("TRAILER")
        }
        if(carTypes[2] == '1'){
            carTypeList.add("CARAVAN")
        }
        if(carTypes[3] == '1'){
            carTypeList.add("MOTERHUM")
        }
        return carTypeList
    }

    override fun getFilterTypeList(): PlaceDto.GetFilterTypeListRes {
        val regionTypeList = CommonUtils.getRegions()
        val carTypeList = CommonUtils.getCarType()
        val campTypeList = CommonUtils.getCampType()
        val facilityTypeList = repo.placeConfigRepository.findAllByPlaceConfigType(ConfigType.FACILITIES)?.map { it.placeConfigKey }
        val amenityTypeList = repo.placeConfigRepository.findAllByPlaceConfigType(ConfigType.AMENITIES)?.map { it.placeConfigKey }
        val ruleTypeList = repo.placeConfigRepository.findAllByPlaceConfigType(ConfigType.RULES)?.map { it.placeConfigKey }
        val envTypeList = CommonUtils.getEnvType()
        return PlaceDto.GetFilterTypeListRes(
            OK,
            regionTypeList = regionTypeList,
            carTypeList = carTypeList,
            campTypeList = campTypeList,
            facilityTypeList = facilityTypeList!!,
            amenityTypeList = amenityTypeList!!,
            ruleTypeList = ruleTypeList!!,
            envTypeList = envTypeList
        )
    }

    override fun getDetail(placeId: String): PlaceDto.GetPlaceDetailRes {
        val place = repo.placeRepository.findByIdAndActive(placeId)
            ?: return PlaceDto.GetPlaceDetailRes(PLACE_NOT_FOUND)

        return PlaceDto.GetPlaceDetailRes(
            OK,
//            buildPlaceDetail(place)
        )

    }
//    private fun buildPlaceDetail(
//        place: Place
//    ): PlaceDto.GetPlaceDetailRes.PlaceDetail{
//        return PlaceDto.GetPlaceDetailRes.PlaceDetail(
//            id = place.id!!,
//            name = place.name,
//            address = place.address,
//            oldAddress = place.oldAddress,
//            bio = place.bio,
//            isClosed = place.isClosed,
//            lat = place.lat,
//            lng = place.lng,
//            likes = place.likes,
//            price = place.price,
//            priceDescription = place.priceDescription,
//            contact = place.contact,
//            websiteUrl = place.websiteUrl,
//            score = place.score,
//            view = place.view,
//            themeId = place.themeId,
//            region = place.region,
//            mainImageId = place.mainImageId,
//            search = place.search,
//            filter = place.filter,
//            isCampsite = place.isCampsite
//        )
//    }

    override fun delete(req: PlaceDto.DeletePlaceReq): PlaceDto.DeletePlaceRes {
        TODO("Not yet implemented")
    }
}