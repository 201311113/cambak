package com.example.cambak.cambak.domains.place.controller

import com.example.cambak.cambak.common.util.Response
import com.example.cambak.cambak.domains.place.service.PlaceDevService
import com.example.cambak.database.entity.place.Place
import com.example.cambak.database.entity.place.PlaceTemp
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/dev/place")
@Api(tags = ["[Dev Place] 개발자 장소 데이터 관리 관련 API"])
class PlaceDevController {
    @Autowired
    lateinit var placeDevService: PlaceDevService

    @GetMapping("")
    fun getPlace(

    ): List<Place>{
        return placeDevService.migration()
    }

}