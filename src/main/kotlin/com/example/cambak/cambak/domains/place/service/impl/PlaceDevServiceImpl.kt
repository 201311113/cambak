package com.example.cambak.cambak.domains.place.service.impl

import com.example.cambak.cambak.common.util.RepositoryProvider
import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.domains.place.service.PlaceDevService
import com.example.cambak.database.entity.place.Place
import com.example.cambak.database.entity.place.PlaceTemp
import com.example.cambak.database.entity.place.Region
import org.springframework.stereotype.Service

@Service
class PlaceDevServiceImpl(
    var repo: RepositoryProvider
): PlaceDevService {

    override fun migration(): List<Place> {
//
//        val list = repo.placeTempRepository.findAll().map {
//            Place(
//                name = it.name,
//                address = it.address,
//                oldAddress = it.oldAddress,
//                bio = it.bio,
//                isClosed = it.isClosed != 0L,
//                lat = it.lat!!,
//                lng = it.lng!!,
//                likes = it.likes,
//                price = null,
//                priceDescription = it.price,
//                contact = it.contact,
//                websiteUrl = it.websiteUrl,
//                score = it.score,
//                view = it.view,
//                themeId = it.themeId,
//                region = if(it.regionId == "SEOUL") Region.SEOUL else if (it.regionId == "GYEONGGI") Region.GYEONGGI
//                else if (it.regionId == "JEOLLA") Region.JEOLLA
//                else if (it.regionId == "JEJU") Region.JEJU
//                else if (it.regionId == "GYEONGNAM") Region.GYEONGNAM
//                else if (it.regionId == "GYEONGBUK") Region.GYEONGBUK
//                else if (it.regionId == "GANGWON") Region.GANGWON
//                else if (it.regionId == "CHUNGCHEONG") Region.CHUNGCHEONG
//                else if (it.regionId == "DAEGU") Region.DAEGU
//                else if (it.regionId == "INCHEON") Region.INCHEON
//                else if (it.regionId == "DAEJEON") Region.DAEJEON
//                else if (it.regionId == "BUSAN") Region.BUSAN
//                else if (it.regionId == "GWANGJU") Region.GWANGJU
//                else
//                    if(it.address.substring(0,2) == "경기") Region.GYEONGGI
//                    else if(it.address.substring(0,2) == "강원") Region.GANGWON
//                    else if(it.address.substring(0,2) == "경북") Region.GYEONGBUK
//                    else if(it.address.substring(0,2) == "충남" || it.address.substring(0,2) == "충청" || it.address.substring(0,2) == "충북") Region.CHUNGCHEONG
//                    else if(it.address.substring(0,2) == "인천") Region.INCHEON
//                    else if(it.address.substring(0,2) == "경남" || it.address.substring(0,2) == "울산") Region.GYEONGNAM
//                    else if(it.address.substring(0,2) == "전남" || it.address.substring(0,2) == "전라" || it.address.substring(0,2) == "전북") Region.JEOLLA
//                    else if(it.address.substring(0,2) == "제주") Region.JEJU
//                    else if(it.address.substring(0,2) == "대구") Region.DAEGU
//                    else Region.UNKNOWN
//                ,
//
//                mainImageId = it.imageId,
//                search = it.search,
//                filter = it.filter,
//                isCampsite = it.isCampsite != 0L,
//
//            )
//        }




        return repo.placeRepository.findAll()
    }

}