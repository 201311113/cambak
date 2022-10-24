//package com.example.cambak.cambak.domains.place.service.impl
//
//import com.example.cambak.cambak.common.util.OK
//import com.example.cambak.cambak.common.util.PLACE_NOT_FOUND
//import com.example.cambak.cambak.common.util.RepositoryProvider
//import com.example.cambak.cambak.common.util.USER_NOT_FOUND
//import com.example.cambak.cambak.domains.place.model.PlaceDto
//import com.example.cambak.cambak.domains.place.service.PlaceService
//import com.example.cambak.database.entity.place.Place
//import com.mysql.cj.x.protobuf.Mysqlx.Ok
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.stereotype.Service
//
//@Service
//class PlaceServiceImpl(
//    var repo: RepositoryProvider
//): PlaceService {
//    override fun create(req: PlaceDto.CreatePlaceReq): PlaceDto.CreatePlaceRes {
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
//
//        return PlaceDto.CreatePlaceRes(
//            OK,
//            newPlace.id
//        )
//    }
//
//    override fun update(req: PlaceDto.UpdatePlaceReq): PlaceDto.UpdatePlaceRes {
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
//
//        return PlaceDto.UpdatePlaceRes(
//            OK,
//            place.id
//        )
//
//    }
//
//    override fun getList(): PlaceDto.GetPlaceListRes {
//        val placeList = repo.placeRepository.findAllByActive()
//
//        return PlaceDto.GetPlaceListRes(
//            OK,
//            buildPlaceList(placeList)
//        )
//    }
//    private fun buildPlaceList(
//        placeList: List<Place>?
//    ): List<PlaceDto.GetPlaceListRes.PlaceInfo>{
//        return placeList?.stream()?.map {
//            PlaceDto.GetPlaceListRes.PlaceInfo(
//                placeId = it.id!!,
//                name = it.name,
//                address = it.address,
//                addressDetail = it.addressDetail,
//                lat = it.lat,
//                lng = it.lng,
//            )
//        }?.toList() ?: emptyList()
//    }
//
//    override fun getDetail(placeId: String): PlaceDto.GetPlaceDetailRes {
//        val place = repo.placeRepository.findByIdAndActive(placeId)
//            ?: return PlaceDto.GetPlaceDetailRes(PLACE_NOT_FOUND)
//
//        return PlaceDto.GetPlaceDetailRes(
//            OK,
//            buildPlaceDetail(place)
//        )
//
//    }
//    private fun buildPlaceDetail(
//        place: Place
//    ): PlaceDto.GetPlaceDetailRes.PlaceDetail{
//        return PlaceDto.GetPlaceDetailRes.PlaceDetail(
//            placeId = place.id!!,
//            name = place.name,
//            address = place.address,
//            addressDetail = place.addressDetail,
//            lat = place.lat,
//            lng = place.lng
//        )
//    }
//
//    override fun delete(req: PlaceDto.DeletePlaceReq): PlaceDto.DeletePlaceRes {
//        TODO("Not yet implemented")
//    }
//}