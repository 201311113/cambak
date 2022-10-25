package com.example.cambak.cambak.domains.place.service.impl

import com.example.cambak.cambak.common.util.OK
import com.example.cambak.cambak.common.util.PLACE_NOT_FOUND
import com.example.cambak.cambak.common.util.RepositoryProvider
import com.example.cambak.cambak.common.util.USER_NOT_FOUND
import com.example.cambak.cambak.domains.place.model.PlaceDto
import com.example.cambak.cambak.domains.place.service.PlaceService
import com.example.cambak.database.entity.place.Place
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

    override fun getList(): PlaceDto.GetPlaceListRes {

        val context = SecurityContextHolder.getContext()

        val user = repo.userRepository.findByEmailAndActive(context.authentication.name)
            ?: return PlaceDto.GetPlaceListRes(USER_NOT_FOUND)


        val placeList = repo.placeRepository.findAll()

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
                address = it.address,
                lat = it.lat,
                lng = it.lng,
            )
        }?.toList() ?: emptyList()
    }

    override fun getDetail(placeId: String): PlaceDto.GetPlaceDetailRes {
        val place = repo.placeRepository.findByIdAndActive(placeId)
            ?: return PlaceDto.GetPlaceDetailRes(PLACE_NOT_FOUND)

        return PlaceDto.GetPlaceDetailRes(
            OK,
            buildPlaceDetail(place)
        )

    }
    private fun buildPlaceDetail(
        place: Place
    ): PlaceDto.GetPlaceDetailRes.PlaceDetail{
        return PlaceDto.GetPlaceDetailRes.PlaceDetail(
            id = place.id!!,
            name = place.name,
            address = place.address,
            oldAddress = place.oldAddress,
            bio = place.bio,
            isClosed = place.isClosed,
            lat = place.lat,
            lng = place.lng,
            likes = place.likes,
            price = place.price,
            priceDescription = place.priceDescription,
            contact = place.contact,
            websiteUrl = place.websiteUrl,
            score = place.score,
            view = place.view,
            themeId = place.themeId,
            region = place.region,
            mainImageId = place.mainImageId,
            search = place.search,
            filter = place.filter,
            isCampsite = place.isCampsite
        )
    }

    override fun delete(req: PlaceDto.DeletePlaceReq): PlaceDto.DeletePlaceRes {
        TODO("Not yet implemented")
    }
}